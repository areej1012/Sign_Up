package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.signup.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.extras!!.getSerializable("user") as User
        binding.tvEmail.text = " Welcome ${user.email}"
        binding.tvMobile.text = " Your number is ${user.mobile}"
        binding.tvLocation.text = " You live in ${user.location}"
        binding.tvPassword.text = " Your password is ${user.password}"
    }

    fun SignOut(view: View) {
        finish()
    }


}