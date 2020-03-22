package com.example.android_db


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_db.Data.StudentsDB.Companion.idStudentsList
import android.view.View
import android.widget.*
import com.example.android_db.Data.StudentsDB
import kotlinx.android.synthetic.main.activity_ver_estudiantes.*

class VerEstudiantes : AppCompatActivity() {

    val studentsDB = StudentsDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_estudiantes)

        if(studentsDB.readAllUsersString().size > 0){

            val adapter = ArrayAdapter<String>(this@VerEstudiantes,android.R.layout.simple_list_item_1,studentsDB.readAllUsersString())
            ltvListasEstudiantes.adapter = adapter

            ltvListasEstudiantes.setOnItemClickListener{adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSelected = adapterView.getItemAtPosition(position)
                val ids = idStudentsList[id.toInt()] // ojo con este

                Toast.makeText(
                    this@VerEstudiantes,  "Seleccionaste el $ids ", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@VerEstudiantes, Detalle_Estudiante::class.java)
                intent.putExtra("ID", ids.trim())
                startActivity(intent)
                studentsDB.studentsGetAll()

            }
        }


        }

    }

