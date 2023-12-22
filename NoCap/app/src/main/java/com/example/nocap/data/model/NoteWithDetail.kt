package com.example.nocap.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
data class NoteWithDetail (
    @Embedded val note: Note,
    @Relation(
        parentColumn = "id",
        entityColumn = "noteId"
    ) val noteDetail: List<NoteDetail>
)