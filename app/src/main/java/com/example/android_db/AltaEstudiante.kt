package com.example.android_db

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_db.Data.StudentsDB
import com.example.android_db.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_alta_estudiante.*
import java.util.*

class AltaEstudiante : AppCompatActivity() {

    val studentsDb = StudentsDB(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_estudiante)


        btnInsertar.setOnClickListener {
            val newstudents = StudentsEntity()

            newstudents.name = edtNombreAlta.text.toString()
            newstudents.lastName = edtApellidosAlta.text.toString()

            val selectGenero = rdGenero.checkedRadioButtonId
            if (selectGenero != -1) {
                when (selectGenero) {
                    rdMasculino.id -> {
                        newstudents.gender = 1
                    }
                    rdFemenino.id -> {
                        newstudents.gender = 0
                    }
                }
            }


                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_, year, month, dayOfMonth ->
                    now.set(Calendar.YEAR,year)
                    now.set(Calendar.MONTH,month)
                    now.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                },
                    now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
                datePicker.show()

            newstudents.birthDay = btnFecha.text.toString()


            Log.d("UDELP", studentsDb.studenAdd(newstudents).toString())
            studentsDb.studentsGetAll()
        }



        }

    }











