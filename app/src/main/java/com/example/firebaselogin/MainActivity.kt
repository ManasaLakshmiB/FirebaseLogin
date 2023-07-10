package com.example.firebaselogin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import com.facebook.AccessToken
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.RuntimeException
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FacebookAuthProvider


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 123
    private lateinit var signInRequest: BeginSignInRequest
    private val TAG: String? = "MAIN_ACTIVITY"
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient




    public override fun onStart() {
        super.onStart()
        //check if user is signed in (non-null) and update UI accordingly
        val currentUser = auth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analytics = Firebase.analytics


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth

        findViewById<Button>(R.id.Crashbutton).setOnClickListener {
            throw RuntimeException("you did it")
        }
        val emailInput = findViewById<EditText>(R.id.etemail)
        val passwordInput = findViewById<EditText>(R.id.etpassword)
        val userName = findViewById<TextView>(R.id.tvName)

        findViewById<Button>(R.id.button).setOnClickListener {
            logEvent()


            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            //assuming both are valid inputs
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        userName.text = user?.displayName ?: user?.email
                        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, EmployeeDetails::class.java)
//                            .putExtra("email", email)
//
//                        startActivity(intent)
//                        finish()
                    } else {
                        //if sign in fails,display a message to the user
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        userName.text = task.exception?.localizedMessage
                    }
                }

        }

        findViewById<Button>(R.id.btnsignup).setOnClickListener {
            logEvent()

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            //assuming both are valid inputs
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        userName.text = user?.displayName ?: user?.email
                        Toast.makeText(this, "singedup", Toast.LENGTH_SHORT).show()
                    } else {
                        //if sign in fails,display a mssage to the user
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        userName.text = task.exception?.localizedMessage
                    }
                }
        }

        findViewById<Button>(R.id.btngoogle).setOnClickListener {

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }



    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    Toast.makeText(this, "success:${user?.email}", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    //updateUI(null)
                    Toast.makeText(this, "Failed:${task.exception.toString()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

            val credential = FacebookAuthProvider.getCredential(token.token)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        // updateUI(null)
                    }
                }

    }


    private fun logEvent() {
        analytics.logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT,
            bundleOf(
                FirebaseAnalytics.Param.ITEM_ID to "123",
                FirebaseAnalytics.Param.ITEM_NAME to "CLICK",
                FirebaseAnalytics.Param.CONTENT_TYPE to "BUTTON_PRESS"
            )
        )
    }
}