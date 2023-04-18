package com.ultraspeeddemon.ethancurtis1166648final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    // See: https://firebase.google.com/docs/auth/android/firebaseui
    //This is a prebuilt UI that allows you to quickly and easily integrate Firebase Authentication into your app.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) //set layout

        // Choose authentication providers
        val providers = arrayListOf(
            //email login
            AuthUI.IdpConfig.EmailBuilder().build(),
            //google login
            AuthUI.IdpConfig.GoogleBuilder().build())

        // Create and launch sign-in intent / Prebuilt UI
        val signInIntent = AuthUI.getInstance() //get instance of firebase auth
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            if(response != null) {
                Toast.makeText(this, "Sign In has Succeeded!",Toast.LENGTH_LONG).show() //toast message for sign in success
            }
            //check if first time login
            val user = FirebaseAuth.getInstance().currentUser //get user
            //get user id
            //go to main screen after sign in
            val intent = Intent(this, MainActivity::class.java) //sends user to main activity
            startActivity(intent)
            finish()
        } else {
            // Sign in failed. If response is null the user canceled the sign-in flow using the back button. Otherwise check
            Toast.makeText(this, "Sign In has Failed!",Toast.LENGTH_LONG).show() //toast message for sign in failure
            startActivity(Intent(this,LoginActivity::class.java)) //return to start screen
        }
    }
}