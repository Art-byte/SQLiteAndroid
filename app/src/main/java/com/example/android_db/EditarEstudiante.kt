package com.example.android_db

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android_db.Data.StudentsDB
import com.example.android_db.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_alta_estudiante.*
import kotlinx.android.synthetic.main.activity_editar_estudiante.*
import java.util.*

class EditarEstudiante : AppCompatActivity() {

    val studentsDB = StudentsDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_estudiante)

        val id = intent.extras?.getString("ID")

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtFechaEdit.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    val mes = (monthOfYear + 1)
                    edtFechaEdit.setText("" + dayOfMonth + "-" + mes.toString() + "-" + year)
                },
                year,
                month,
                day
            )
            dpd.show()

        }
        //-----------------------DatePicker----------------------
        var student = StudentsEntity()
        if (id != null) {
            student = studentsDB.studentsGetOne(id.toInt())
        }


        edtNombreEdit.setText("${student.name.toString()}")
        edtApellidosedit.setText("${student.lastName.toString()}")
        when (student.gender) {
            1 -> {
                txvGeneroEdit.setText("Masculino")
            }
            2 -> {
                txvGeneroEdit.setText("Femenino")
            }

        }


        edtFechaEdit.setText(student.birthDay)

        //----------------TEST
        btnEditar.setOnClickListener {
            val n = StudentsEntity()

            if(edtNombreEdit.text.toString().trim().isNotEmpty()){
                var nomeEdit = edtNombreEdit.text.toString()

                if(edtApellidosedit.text.toString().trim().isNotEmpty()){
                    var ApEdit = edtApellidosedit.text.toString()

                    val selectGenero = rdGeneroEdit.checkedRadioButtonId
                    if (selectGenero != -1) {

                        when (selectGenero) {
                            rdFemeninoEdit.id -> {
                                n.gender = 0
                            }
                            rdMasculinoEdit.id -> {
                                n.gender = 1
                            }

                        }
                    }

                    var fecha = edtFechaEdit.text.toString()
                    var values = StudentsEntity(-1,student.name,student.lastName,student.gender,student.birthDay)
                    var id = studentsDB.studenAdd(values)


                    edtFechaEdit.setText("Fecha nacimiento")



                    Toast.makeText(this@EditarEstudiante,"Registrado", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this@EditarEstudiante,"Ingresar Apellido",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditarEstudiante,"Ingresar Nombre",Toast.LENGTH_LONG).show()
            }
        }
    }
}






