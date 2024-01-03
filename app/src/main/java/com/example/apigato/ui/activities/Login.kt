package com.example.apigato.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apigato.databinding.LoginBinding

class Login: AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onPostCreate(savedInstanceState: Bundle?) {

        super.onPostCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide();

        binding.login.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

    }
}