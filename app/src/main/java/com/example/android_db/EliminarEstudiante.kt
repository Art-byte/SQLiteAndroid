package com.example.android_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.android_db.Data.StudentsDB
import kotlinx.android.synthetic.main.activity_eliminar_estudiante2.*
import com.example.android_db.Data.StudentsDB.Companion.idStudentsList


class EliminarEstudiante : AppCompatActivity() {
    val studentsDb = StudentsDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_estudiante2)

        if( studentsDb.readAllUsersString().size >0){
            val Adaptador = ArrayAdapter<String> (this@EliminarEstudiante, android.R.layout.simple_list_item_1,studentsDb.readAllUsersString())

            ltvListaEstudiantesDel.adapter= Adaptador

            ltvListaEstudiantesDel.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)

                // var index= listStringIndex[position]
                var idStudent =  StudentsDB.idStudentsList[id.toInt()]
                Toast.makeText(
                    this@EliminarEstudiante,

                    "Seleccionaste el $idStudent ",
                    Toast.LENGTH_SHORT
                ).show()
                miDialogo("Eliminar a ${idStudent.trim().toString()}",idStudent.trim().toInt()).show()

            }

        }

    }

    private fun miDialogo(texto: String, id: Int): AlertDialog {
        val miAlerta = AlertDialog.Builder(this@EliminarEstudiante)
        miAlerta.setTitle("Estar seguro que deseas eliminar?").setMessage(texto)
        miAlerta.setPositiveButton("SI"){dialog,which ->
            studentsDb.studentDelete(id)
            Toast.makeText(this@EliminarEstudiante,"ok, Eliminado", Toast.LENGTH_SHORT).show()
            finish();
            startActivity(intent);
        }
        miAlerta.setNegativeButton("NO"){ dialog,which ->
            Toast.makeText(this@EliminarEstudiante,"Negativo carnal", Toast.LENGTH_SHORT).show()

        }
        return miAlerta.create()
    }
    }

