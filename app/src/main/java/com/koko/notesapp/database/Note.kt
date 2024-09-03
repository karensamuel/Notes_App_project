package com.koko.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// create Table note (_id integer primary key autoincrement, details text not null)
@Entity(tableName = "note")
class Note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    val title: String,
    val details: String
)