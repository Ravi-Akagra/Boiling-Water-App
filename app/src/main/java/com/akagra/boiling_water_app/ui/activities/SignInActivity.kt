package com.akagra.boiling_water_app.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.akagra.boiling_water_app.extra.GoogleAuth
import com.akagra.boiling_water_app.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var googleAuth: GoogleAuth

    private var googleSignInBtn: SignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        supportActionBar?.hide()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        googleSignInBtn = binding.googleSigninBtn
//        setupGoogleSignin()
        googleAuth = GoogleAuth(this)
        if (googleAuth.acct != null) {
            navigateToSecondActivity()
        }

        googleSignInBtn!!.setOnClickListener {
            signIn()
        }
    }

    fun signIn() {
        val intent = googleAuth.gsc!!.signInIntent
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                navigateToSecondActivity()
            } catch (e: ApiException) {
                print("asasasasa: ")
                println(e)
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
//    private fun setupGoogleSignin(){
//        gso =
//            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
//        gsc = GoogleSignIn.getClient(this, gso!!)
//
//        val acct = GoogleSignIn.getLastSignedInAccount(this)
//        if (acct != null) {
//            navigateToSecondActivity(acct)
//        }
//    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, HomePageActivity::class.java )
//        intent.putExtra("DisplayName",acct.displayName)
//        intent.putExtra("DisplayImg",acct.photoUrl.toString())
        startActivity(intent)
        finish()
    }

//    fun signIn() {
//        val signInIntent: Intent = gsc!!.signInIntent
//        startActivityForResult(signInIntent, 1000)
//    }
//
//     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1000) {
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                task.getResult(ApiException::class.java)
//                navigateToSecondActivity()
//            } catch (e: ApiException) {
//                print("asasasasa: ")
//                println(e)
//                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }

}
