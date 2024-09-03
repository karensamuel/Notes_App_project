package com.koko.notesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAo {
    // dont need to send every thing it already nows
   /* //insert into note(details value ("...")
    @Insert
    fun insertNote(n: Note)
    //update note set details = "..." where id = ...
    @Update
    fun updateNote(n: Note)
    //delete from note where id = ...*/
    //we can replace insert and update
    @Upsert
    // if i send id and details -> update if send details only that means its new -> insert
    suspend fun upsertNote(n: Note)

    @Delete
    suspend fun deleteNote(n: Note)
    // flow already supports suspend function
    //select * from note
    @Query("select * from note")
    fun getNotes(): Flow<List<Note>>
    //select * from note where id = ...


}