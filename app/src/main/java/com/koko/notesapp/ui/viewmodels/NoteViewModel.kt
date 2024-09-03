package com.koko.notesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koko.notesapp.database.Note
import com.koko.notesapp.database.RoomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (app: Application): AndroidViewModel(app){
    val db = RoomDBHelper.getInstance(app)
   /* fun upsert (n:Note){
        db.dao.upsertNote(n)
    }*/
    // cant call a suspended function from nonsuspended function
    fun upsert(n: Note) {
        viewModelScope.launch (Dispatchers.IO){
            db.dao.upsertNote(n)
        }

    }
    /*fun delete (n:Note){
        db.dao.deleteNote(n)
    }*/
    fun delete(n: Note) {
        viewModelScope.launch (Dispatchers.IO){
            db.dao.deleteNote(n)
        }

    }
   /* fun getNotes(): Flow<List<Note>> {
        return db.dao.getNotes()
    }*/
    fun getNotes() = db.dao.getNotes()

}