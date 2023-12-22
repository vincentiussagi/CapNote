package com.example.nocap.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nocap.data.model.Note
import com.example.nocap.data.model.NoteDetail
import com.example.nocap.data.model.NoteWithDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNoteDetail(noteDetail: NoteDetail)

    @Update
    fun update(note: Note)
    @Delete
    fun delete(note:Note)

    @Update
    fun updateNoteDetail(noteDetail: NoteDetail)
    @Delete
    fun deleteNoteDetail(noteDetail: NoteDetail)

    @Query ("SElECT * FROM Note")
    fun getAll():Flow<List<Note>>
    @Query ("SElECT * FROM Note WHERE id = :noteId")
    fun getById(noteId:Int):Flow<List<Note>>
    @Query("Select * FROM Note WHERE id = :noteId")
    fun getNoteWithDetail(noteId:Int):Flow<NoteWithDetail>
}