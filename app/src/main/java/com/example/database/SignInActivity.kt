package com.example.database


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var  databaseReference: DatabaseReference

    companion object{
        const val KEY1 ="com.example.database.SignInActivity.mail"
        const val KEY2 ="com.example.database.SignInActivity.name"
        const val KEY3 ="com.example.database.SignInActivity.id"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in )

        supportActionBar?.hide()

        val signInButton = findViewById<Button>(R.id.SignInbtn)
            val userName = findViewById<TextInputEditText>(R.id.UserName)


        signInButton.setOnClickListener {

            //take refrance tile database node "User"

            val uniqueId  = userName.text.toString()

            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }
            else
            {
                Toast.makeText( this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // onCreate Method Over

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(uniqueId).get().addOnSuccessListener {

             //if user exis or not
            if(it.exists()){
                //  welcome user in ypur app, with intent and also pass

                val email = it.child("email").value
                val name = it.child( "name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())

                intentWelcome.putExtra(KEY2, name.toString())

                intentWelcome.putExtra(KEY3, userId.toString())
                startActivity(intentWelcome)
            } else{

                Toast.makeText( this, "User Dose not Exist in Database", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {

            Toast.makeText( this, "Failed to load database", Toast.LENGTH_SHORT).show()
        }
    }


}
