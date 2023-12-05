package com.example.nocap

import android.content.Intent
import android.net.wifi.hotspot2.pps.Credential
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.oAuthCredential

class MainActivity2 : AppCompatActivity() {
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn = findViewById(R.id.button)
        btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i  = Intent(this,MainActivity::class.java)
            startActivity(i)

        }
    }
}