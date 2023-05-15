package com.example.android1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private lateinit var etUsername: EditText
private lateinit var etPassword: EditText
private lateinit var password: String
private lateinit var userName: String

class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)

        var etButton = findViewById<Button>(R.id.btnRegister)

        //navigation
        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        etButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()

        if (userName.isEmpty()) {
            etUsername.error ="Username is required"
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()) {
            etPassword.error = "Password is required"
            etPassword.requestFocus()
            return
        }
        val call: Call<ResponseBody> = RetroFitClient
            .getInstance()
            .api
            .createUser(User(userName, password))

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == "SUCCESS") {
                    Toast.makeText(
                        this@Register,
                        "Successfully registered. Please login",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@Register, Login::class.java))
                } else {
                    Toast.makeText(this@Register, "User already exists!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable){
                Toast.makeText(this@Register, t.message, Toast.LENGTH_LONG).show()

            }
        })
    }
}




