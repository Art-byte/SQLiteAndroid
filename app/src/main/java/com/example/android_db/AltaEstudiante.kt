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

    val studentsDb = StudentsDB()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_estudiante)


        btnInsertar.setOnClickListener {
            val newstudents = StudentsEntity(-1, name = "", lastName = "",gender =0 )

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

            Log.d("UDELP", studentsDb.studenAdd(newstudents).toString())
            studentsDb.studentsGetAll()



        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtFecha.setOnClickListener {
            val newstudents = StudentsEntity()

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                val mes= (monthOfYear + 1)
                edtFecha.setText("" + dayOfMonth + "-" + mes.toString() + "-" + year).toString()
            }, year, month, day)
            dpd.show()

            var nacimiento = edtFecha.text.toString()
            newstudents.birthDay= nacimiento
            Log.d("UDELP", studentsDb.studenAdd(newstudents).toString())
            studentsDb.studentsGetAll()
        }




    }

    }


/*•

   var values = StudentsEntity(-1,nombreAlta,ApAlta,AmAlta,generoposition,selectedNivelAcademico,escuelaposition,phone,correo,nacimiento)
                                                var id = studentsDb.studentAdd(values)
                                                Log.d("UDELP","El elemento guardado es $id")



var values = StudentsEntity(-1,"Andres","Chavarria","Chavez",1,2,2,"5513107596","andrew_f19@hotmail.com","1994/08/31")
        var id = studentsDb.studentAdd(values)
fun  studentAdd(student:StudentsEntity): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(GENDER,student.gender)
        values.put(ACADEMICLEVEL,student.academicLevel)
        values.put(PREVIOUSSCHOOL,student.previousSchool)
        values.put(PHONE,student.phone)
        values.put(EMAIL,student.email)
        values.put(BIRTHDAY,student.birthday)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_STUDENTS,null,values)
        ]*/










