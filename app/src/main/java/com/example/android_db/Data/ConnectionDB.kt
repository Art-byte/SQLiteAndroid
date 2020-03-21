package com.example.android_db.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConnectionDB(contexto: Context): SQLiteOpenHelper (contexto, DATABASE_NAME,null, DATABASE_VERSION){

    companion object{
        const val DATABASE_NAME ="UNIVERSIDAD"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME_STUDENTS = "CTL_Estudiantes"
        const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME_STUDENTS(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(20), LastName VARCHAR(15), Gender INTEGER, BirthDay DATE )"
        const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_STUDENTS"
        const val MODE_WRITE = 1
        const val MODE_READ = 2

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
    }

    fun openConnection(typeConnectionDB: Int): SQLiteDatabase{
        return when(typeConnectionDB){
            MODE_WRITE ->
                return writableDatabase
            MODE_READ ->
                return readableDatabase
            else ->
                return readableDatabase
        }

    }


}