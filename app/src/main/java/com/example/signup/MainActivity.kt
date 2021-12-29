package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.signup.databinding.ActivityHomeBinding
import com.example.signup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dbHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun signInUser(view: View) {
        startActivity(Intent(this , SginInAtivity::class.java))
    }
    fun logInUser(view: View) {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this , "Fill all fields",Toast.LENGTH_SHORT).show()
        }
        else{
            val user = dbHelper.readUser(email)
            if (user == null){
                Toast.makeText(this , "There is no user with is email",Toast.LENGTH_LONG).show()
            }
            else{
                if (password==(user.password)){
                    val intent = Intent(this , HomeActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this , "Your password is wrong",Toast.LENGTH_LONG).show()
            }
        }


    }


}