package com.example.nocap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.example.nocap.data.DatabaseHelper
import com.example.nocap.data.model.Note
import com.example.nocap.data.model.NoteDetail
import com.example.nocap.data.model.NoteWithDetail
import com.example.nocap.databinding.ActivityCreateNewTopicBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewTopicBinding
    private lateinit var adapter: DetailAdapter
    private lateinit var roomDb: DatabaseHelper
    private var noteId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiate adapter
        roomDb = DatabaseHelper.getInstance(this)
        adapter = DetailAdapter()

        var note: NoteWithDetail? = null
        noteId = intent.getIntExtra("noteId",0)

        if (noteId != null && noteId != 0){
            CoroutineScope(Dispatchers.IO).launch {
                roomDb.todoDao().getNoteWithDetail(noteId!!).collect(){
                    note = it
                    CoroutineScope(Dispatchers.Main).launch {
                        adapter.updateDataset(note?.noteDetail ?: listOf())
                        binding.tvTitle.setText(note?.note?.title ?: "Enter Title")
                    }
                }
            }
        }

        binding.rvContent.adapter = adapter

        binding.btnAdd.setOnClickListener {
            MaterialDialog(this).show {
                customView(R.layout.dialog_add_content)
                val text = getCustomView().findViewById<EditText>(R.id.dialog_tv_item)
                val btnAdd = getCustomView().findViewById<Button>(R.id.dialog_btn_add)

                btnAdd.setOnClickListener {
                    adapter.addDataset(NoteDetail(0, note?.note?.id ?: 0, text = text.text.toString()))
                    this.dismiss()
                }
            }
        }

        binding.btnSaveData.setOnClickListener {
            if (note == null) {
                CoroutineScope(Dispatchers.IO).launch {
                    roomDb.todoDao().insert(Note(0, binding.tvTitle.text.toString()))
                    for (item in adapter.getDataset()){
                        roomDb.todoDao().insertNoteDetail(item)
                    }

                }.invokeOnCompletion {
                    try {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                applicationContext,
                                "Saved Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        return@invokeOnCompletion
                    }
                }
            }
        }
    }
}