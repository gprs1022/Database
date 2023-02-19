package com.example.database

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.hide()

        val name = intent.getStringExtra(SignInActivity.KEY2)

        val mail = intent.getStringExtra(SignInActivity.KEY1)

        val userId = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        val mailText = findViewById<TextView>(R.id.tVMail)
        val idText = findViewById<TextView>(R.id.tVUniqueId)
        welcomeText.text = "Welcome$name"

        mailText.text ="Mail: $mail"
        idText.text = "UserId :$userId"
    }
}