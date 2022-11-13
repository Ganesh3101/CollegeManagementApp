package com.college.collegemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.college.collegemanagementapp.DataClasses.GoogleData
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class LoginPage : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        firebaseAuth = FirebaseAuth.getInstance()

        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        val googleLoginButton = findViewById<SignInButton>(R.id.sign_in_button)
        googleLoginButton.setOnClickListener {
            signIn()
        }

    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                val email = account.email
                val s = email?.split("@")
                val domain = s?.get(1)
                if(domain=="somaiya.edu"){
                    Google_auth(account)
                }
                else{
                    Toast.makeText(this,"Use verified organization email-id",Toast.LENGTH_LONG).show()
                }

            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun Google_auth(account: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val googleFirstName = account?.givenName ?: ""

                val googleLastName = account?.familyName ?: ""

                val googleEmail = account?.email ?: ""

                var googleProfilePicURL = account?.photoUrl.toString()

                var prn_number = "null"

                var googleAdmin : String

                if(googleEmail == "jay.nk@somaiya.edu"){
                    googleAdmin = "Admin"
                }
                else{
                    googleAdmin = null.toString()
                }

                val ref  = FirebaseDatabase.getInstance().getReference("Users").child("Google")
                val proc_ref  = FirebaseDatabase.getInstance().getReference("Proctor_list").child(googleFirstName)

                val currentUser : String? = Firebase.auth.currentUser?.uid

                val Google_Upload= GoogleData(googleFirstName, googleLastName, googleEmail, googleProfilePicURL,prn_number,0)

                val Google_Upload_Proc= ProctorsetData(googleFirstName, googleLastName,  googleProfilePicURL,googleAdmin)

                try {
                    ref.child(currentUser.toString()).setValue(Google_Upload).addOnCompleteListener {

                        Toast.makeText(this, "data saved successfully", Toast.LENGTH_LONG).show()

                    }
                }
                catch (e: Exception)
                {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

                }

                try {
                    proc_ref.setValue(Google_Upload_Proc).addOnCompleteListener {

                       // Toast.makeText(this, "data saved successfully", Toast.LENGTH_LONG).show()

                    }
                }
                catch (e: Exception)
                {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

                }

                val baos = ByteArrayOutputStream()

                val image = baos.toByteArray()

                val storageRef = FirebaseStorage.getInstance().reference.child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")


                val upload = storageRef.putBytes(image)

                upload.addOnCompleteListener{ uploadTask->
                    if (uploadTask.isSuccessful)
                    {
                        storageRef.downloadUrl.addOnCompleteListener{ urlTask->
                            urlTask.result?.let {
                                googleProfilePicURL = it.toString()

                            }

                        }
                    }
                    else{
                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                    }
                }

                val intent = Intent(this, ProfilePage::class.java)
                startActivity(intent)

               

            }
        }
    }


}