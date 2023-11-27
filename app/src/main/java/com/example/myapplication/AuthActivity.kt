package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val userEmail =
            findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.emailEditText)
        val userPass =
            findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.passEditText)
        val registerButton =
            findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.registerButton)
        val intent = Intent(this, MainActivity::class.java)

        var email: String
        var pass: String

        registerButton.setOnClickListener {
            email = userEmail.text.toString()
            pass = userPass.text.toString()

            if (isValidEmail(userEmail.text.toString()) && isValidPass(userPass.text.toString())) {
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Invalid email or password.", Toast.LENGTH_SHORT
                ).show()
            }
        }

        userEmail.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                email = userEmail.text.toString()
                if (isValidEmail(email)) {
                    true
                } else {
                    userEmail.error = "Use the correct email format"
                    true
                }
            } else {
                false
            }
        }

        userEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                email = userEmail.text.toString()
                if (isValidEmail(email)) {
                    // to do
                } else {
                    userEmail.error = "Use the correct email format"
                }
            }
        }


        userPass.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                pass = userPass.text.toString()
                if (isValidPass(pass)) {
                    true
                } else {
                    userPass.error = "Password: 6+ chars, Aa-Zz, 0-9"
                    true
                }
            } else {
                false
            }
        }

        userPass.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                pass = userPass.text.toString()
                if (isValidPass(pass)) {
                    // to do
                } else {
                    userPass.error = "Password: 6+ chars, Aa-Zz, 0-9"
                }
            }
        }

    }


    private fun isValidPass(pass: String): Boolean {
        val regex = Regex("^[A-Za-z0-9]{6,}$")
        return regex.matches(pass)
    }

    private fun isValidEmail(email: String): Boolean {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

}