package com.koko.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// can use this code in any other projects
@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class RoomDBHelper: RoomDatabase() {


abstract val dao: NoteDAo
   companion object{
       // used  so any update appear
       @Volatile
       //race condition

       //helps to return only 1 object
    private  var INSTANCE: RoomDBHelper? = null //null bec object is still not made
       fun getInstance(context: Context): RoomDBHelper {
          /* if(INSTANCE == null){
               //create A NEW DB object
           }else{
               //retrive the old one
           }*/
           // using elves operator
           // syncronize ->locks
           return INSTANCE?: synchronized(this){
               //  I want to lock first one happen then secod not with each others  and bec its volatile any update will apear
               // instance build only once at first
               val instance = Room.databaseBuilder(context, RoomDBHelper::class.java, "MyDB")
               //ANR -> application not responding
               //.allowMainThreadQueries() dont needed after putting suspend key word in dao
               .build()
               INSTANCE = instance
               // we cant use INSTANCE bec its nullable so we can use INSTANCE!! or instance
               instance
           }
       }

   }

}