package com.example.android_db.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class StudentsDB {
    private var connectionDB: ConnectionDB
    private lateinit var sqliteDatabase: SQLiteDatabase


    constructor(context: Context) {
        connectionDB = ConnectionDB(context)
    }

    companion object {
        const val ID = "id"
        const val NAME = "Name"
        const val LASTNAME = "LastName"
        const val BIRTHDAY = "Birthday"
        const val GENDER = "Gender"

        private  val studentsStrings = arrayListOf<String>()
        val idStudentsList = arrayListOf<String>()

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
        sqliteDatabase.delete(ConnectionDB.TABLE_NAME_STUDENTS,selection, args)
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

    fun readAllUsersString(): Array<String> {
        studentsStrings.clear()
        idStudentsList.clear()
        sqliteDatabase= connectionDB.openConnection(ConnectionDB.MODE_READ)
        val fields = arrayOf(NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqliteDatabase.query(
            ConnectionDB.TABLE_NAME_STUDENTS,
            fields,
            null,
            null,
            null,
            null,
            null
        )
        if(cursor.moveToFirst()){
            do{
                studentsStrings.add("${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")

                Log.d("UDELP",
                    "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
                idStudentsList.add("${cursor.getInt(0)}")
            }while(cursor.moveToNext())
        }
        val data = idStudentsList
        return idStudentsList.toTypedArray()


    }




    fun studentsGetOne(IdStudent: Int): StudentsEntity{
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
        var showStudent = StudentsEntity()
        if (cursor.moveToFirst()) {
            var id= cursor.getInt(0)
            var name = cursor.getString(1)
            var lastName = cursor.getString(2)
            var gender = cursor.getInt(3)
            var birthday = cursor.getString(4)

            showStudent = StudentsEntity(cursor.getInt(0), "${name}","${lastName}",cursor.getInt(3),birthday)
            Log.d(
                "UDELP",
                "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}"
            )
        }
        return showStudent

    }
}

/*
Prueba cadenas

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

