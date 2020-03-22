package com.example.android_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_db.Data.StudentsDB
import com.example.android_db.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_detalle__estudiante.*

class Detalle_Estudiante : AppCompatActivity() {

    val studentsDb = StudentsDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle__estudiante)

        val id = intent.extras?.getString("ID")

        var stud = StudentsEntity()
        if(id != null){
            stud = studentsDb.studentsGetOne(id.toInt())
        }

        txvNombreAlumno.setText("${stud.name.toString()}")
        txvNApellidos.setText("${stud.lastName.toString()}")

        when(stud.gender){
            0->{
                txvGenero.setText("Femenino")
            }
            1->{
                txvGenero.setText("Masculino")
            }
        }

        txvFecha.setText(stud.birthDay)


    }
}
