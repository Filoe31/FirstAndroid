package com.example.android1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.LoginFilter.UsernameFilterGMail
import android.widget.TextView

private lateinit var welcomeText: String
private lateinit var tvWelcome: TextView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        welcomeText = "Welcome " + getIntent().getStringExtra("userName" ).toString() + "!";
        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);


    }
}

//fix this code to include the users nameoverride fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)
//
//        val username = intent.getStringExtra("Username")
//        val welcomeText = "Welcome $username!";
//
//        tvWelcome = findViewById(R.id.tvWelcome);
//        tvWelcome.text = welcomeText;
//}