package com.example.nocap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocap.databinding.ActivityCreateNewTopicBinding

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewTopicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}