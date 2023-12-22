package com.example.nocap.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteDetail(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val noteId: Int,
    val img: String = "",
    val text: String
)