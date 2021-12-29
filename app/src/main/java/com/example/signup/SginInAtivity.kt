package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.signup.databinding.ActivitySginInAtivityBinding

class SginInAtivity : AppCompatActivity() {
    lateinit var binding: ActivitySginInAtivityBinding
    private val dbHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySginInAtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun SignInUser(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val mobile = binding.etMobile.text.toString().trim()
        val location = binding.etLocation.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if(email.isEmpty() || password.isEmpty() || mobile.isEmpty() || location.isEmpty()){
            Toast.makeText(this , "Fill all fields", Toast.LENGTH_SHORT).show()
        }
        else{
            val user = User(email, mobile.toInt() , location, password)
            if(dbHelper.saveUser(user)){
                val intent = Intent(this , HomeActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
            else
                Toast.makeText(this , "You are have account",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}