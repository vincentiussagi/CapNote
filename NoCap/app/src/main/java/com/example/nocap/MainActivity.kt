package com.example.nocap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.nocap.data.DatabaseHelper
import com.example.nocap.data.model.Note
import com.example.nocap.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var roomDb: DatabaseHelper
    private val listOfNote = mutableListOf<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiate adapter
        roomDb = DatabaseHelper.getInstance(this)
        adapter = MainAdapter {
            startActivity(Intent(this, CreateActivity::class.java).putExtra("noteId", it.id))
        }
        binding.rvNote.adapter = adapter

        //initiate event handler
        CoroutineScope(Dispatchers.IO).launch {
            fetchData()
        }

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

    suspend fun fetchData() {
        roomDb.todoDao().getAll().collect {
            listOfNote.clear()
            listOfNote.addAll(it)
            CoroutineScope(Dispatchers.Main).launch {
                adapter.updateDataset(listOfNote)
            }
        }
    }
}