package com.example.android_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.android_db.Data.StudentsDB
import kotlinx.android.synthetic.main.activity_lista__estudiantes__edit.*
import com.example.android_db.Data.StudentsDB.Companion.idStudentsList


class Lista_Estudiantes_Edit : AppCompatActivity() {

    val studentsDb = StudentsDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista__estudiantes__edit)

        if( studentsDb.readAllUsersString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@Lista_Estudiantes_Edit, android.R.layout.simple_list_item_1,studentsDb.readAllUsersString())

            ltvMisAlumnos.adapter=miAdaptador

            ltvMisAlumnos.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)

                val idStudent =  StudentsDB.idStudentsList[id.toInt()]
                Toast.makeText(
                    this@Lista_Estudiantes_Edit,

                    "Seleccionaste el $idStudent ",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@Lista_Estudiantes_Edit, EditarEstudiante::class.java)
                intent.putExtra("ID", idStudent.trim())
                startActivity(intent)
            }

        }



    }
}
