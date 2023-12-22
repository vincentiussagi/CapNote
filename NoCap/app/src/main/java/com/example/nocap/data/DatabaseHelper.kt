package com.example.nocap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nocap.data.model.Note
import com.example.nocap.data.model.NoteDetail

@Database(entities = [Note::class,NoteDetail::class], version = 1)
abstract class DatabaseHelper:RoomDatabase() {
    abstract fun todoDao(): NoteDAO

    companion object {
        private const val Database_NAME = "note.db"

        /**
         * As we need only one instance of db in our app will use to store
         * This is to avoid memory leaks in android when there exist multiple instances of db
         */
        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseHelper::class.java,
                        Database_NAME
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}