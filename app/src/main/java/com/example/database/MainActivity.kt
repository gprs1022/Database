package com.example.database



import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val signUpButton = findViewById<Button>(R.id.SignUpbtn)
        val userName    = findViewById<TextInputEditText>(R.id.etName)
        val userMail   = findViewById<TextInputEditText>(R.id.etEmail)
        val userId       = findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)



                signUpButton.setOnClickListener {

                    val name = userName.text.toString()
                    val mail = userMail.text.toString()
                    val uniqueId = userId.text.toString()
                    val usrpassword = userPassword.text.toString()

                    val user = User(name, mail, usrpassword, uniqueId)

                    database = FirebaseDatabase.getInstance().getReference("User")

                    database.child(uniqueId).setValue(user).addOnSuccessListener {


                        Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                    }

                }

        val signIntext = findViewById<TextView>(R.id.tVsignIn)

        signIntext.setOnClickListener {

            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }

    }
}