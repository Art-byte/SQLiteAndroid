package com.example.android_db

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.android_db.Data.StudentsDB
import kotlinx.android.synthetic.main.activity_ver_estudiantes.*

class VerEstudiantes : AppCompatActivity() {

    lateinit var sqliteDatabase: StudentsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_estudiantes)


        val adaptador = ArrayAdapter<String>(
            this@VerEstudiantes,
            android.R.layout.simple_list_item_1,
            sqliteDatabase.readAllUsers()
        )

        /**
         * /lvtEstudiantes.adapter = adaptador

        lvtEstudiantes.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
        val itemSeleccionado = adapterView.getItemAtPosition(position)
        Toast.makeText(
        this@VerEstudiantes,
        "$position $id $itemSeleccionado",
        Toast.LENGTH_SHORT
        ).show()


        // paso de parametros
        val intent = Intent(this@VerEstudiantes, Detalle_Estudiante::class.java)
        intent.putExtra("ID", position.toString())
        startActivity(intent)

         */
        }

    }

