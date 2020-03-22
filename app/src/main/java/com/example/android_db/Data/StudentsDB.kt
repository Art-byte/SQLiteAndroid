package com.example.android_db.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class StudentsDB {
    private var connectionDB: ConnectionDB
    private lateinit var sqliteDatabase: SQLiteDatabase
    private val ListaEstu = arrayListOf<String>()

    constructor(context: Context) {
        connectionDB = ConnectionDB(context)
    }

    companion object {
        const val ID = "id"
        const val NAME = "Name"
        const val LASTNAME = "LastName"
        const val BIRTHDAY = "Birthday"
        const val GENDER = "Gender"

    }

    fun studenAdd(student: StudentsEntity): Long{
        sqliteDatabase = connectionDB.openConnection(ConnectionDB.MODE_WRITE)
        val values = ContentValues()
        values.put(NAME, student.name)
        values.put(LASTNAME, student.lastName)
        values.put(GENDER, student.gender)
        values.put(BIRTHDAY, student.birthDay)

        return sqliteDatabase.insert(ConnectionDB.TABLE_NAME_STUDENTS, null, values)

    }

    fun studentEdit(student: StudentsEntity): Int {
        sqliteDatabase = connectionDB.openConnection(ConnectionDB.MODE_WRITE)
        val values = ContentValues()
        values.put(NAME, student.name)
        values.put(LASTNAME, student.lastName)
        values.put(GENDER, student.gender)
        values.put(BIRTHDAY, student.birthDay)
        var selection = "Id =?"
        var args = arrayOf(student.id.toString())

        return sqliteDatabase.update(ConnectionDB.TABLE_NAME_STUDENTS, values, selection, args)

    }

    fun studentDelete(IdStudent: Int): Int {
        sqliteDatabase = connectionDB.openConnection(ConnectionDB.MODE_WRITE)
        var selection = "Id =?"
        var args = arrayOf(IdStudent.toString())

        return sqliteDatabase.delete(ConnectionDB.TABLE_NAME_STUDENTS, selection, args)

    }

    fun studentsGetAll(){
        sqliteDatabase = connectionDB.openConnection(ConnectionDB.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)
        val cursor = sqliteDatabase.query(
            ConnectionDB.TABLE_NAME_STUDENTS,
            fields,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            do {
                Log.d(
                    "UDELP",
                    "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}"
                )
            } while (cursor.moveToNext())
        }

    }

    fun readAllUsers(): ArrayList<String> {
        val us = StudentsEntity()

        val db = ConnectionDB.MODE_READ
        val fields = arrayOf(NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqliteDatabase.query(
            ConnectionDB.TABLE_NAME_STUDENTS,
            fields,
            "SELECT * FROM",
            null,
            null,
            null,
            null
        )

        var name: String
        var lastname: String
        var gender: Int
        var birthday: String

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(us.name))
                lastname = cursor.getString(cursor.getColumnIndex(us.lastName))
                gender = cursor.getInt(cursor.getColumnIndex(us.gender.toString()))
                birthday = cursor.getString(cursor.getColumnIndex(us.birthDay))

                ListaEstu.add(cursor.getInt(0).toString())
                ListaEstu.add(cursor.getString(1))
                ListaEstu.add(cursor.getString(2))
                ListaEstu.add(cursor.getInt(3).toString())
                ListaEstu.add(cursor.getString(4))

                cursor.moveToNext()
            }
        }
        return ListaEstu
    }




    fun studentsGetOne(IdStudent: Int){
        sqliteDatabase = connectionDB.openConnection(ConnectionDB.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)
        var selection = "Id=?"
        var args = arrayOf(IdStudent.toString())
        val cursor = sqliteDatabase.query(
            ConnectionDB.TABLE_NAME_STUDENTS,
            fields,
            selection,
            args,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {

            Log.d(
                "UDELP",
                "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}"
            )
        }

    }
}

/*
* fun studentsGetAllString(): Array<String>{
        listString.clear()
        listStringIDS.clear()
        //Agregando de una vez el listString
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                listString.add("${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(2)} ${cursor.getString(4)}")
                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(2)} ${cursor.getString(4)}")
                listStringIDS.add("${cursor.getInt(0)} ")
            }while (cursor.moveToNext())

        }
        val elems = listString
        return elems.toTypedArray()

    }*/

