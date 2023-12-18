package com.example.nocap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.nocap.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            MaterialDialog(this)
                .message(text = "Are you sure to logout ?")
                .show {
                    positiveButton(text = "Logout") { _ ->
                        FirebaseAuth.getInstance().signOut()
                        val i = Intent(baseContext, LoginActivity::class.java)
                        startActivity(i)
                    }
                    negativeButton(text = "Cancel") { dialog ->
                        dialog.dismiss()
                    }
                }
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }
}