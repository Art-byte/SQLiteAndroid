package com.example.android_db

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

            if(edtNombreAlta.text.toString().trim().isNotEmpty()){
            var nomAlt = edtNombreAlta.text.toString()

                if(edtApellidosAlta.text.toString().trim().isNotEmpty()){
                    var ApAlta = edtApellidosAlta.text.toString()


// ------------------------------Corregir esta parte
                    val selectGenero = rdGenero.checkedRadioButtonId
                    if (selectGenero != -1) {
                        var genero = 0
                        when (selectGenero) {

                            rdFemenino.id -> {
                                genero = 0
                            }
                            rdMasculino.id -> {
                               genero = 1
                            }

                        }
                    }

                    var fecha = edtFecha.text.toString()
                    var values = StudentsEntity(-1,nomAlt,ApAlta,selectGenero,fecha)
                    var id = studentsDb.studenAdd(values)

                    edtNombreAlta.text.clear()
                    edtApellidosAlta.text.clear()
                    rdGenero.clearCheck()
                    edtFecha.setText("Fecha nacimiento")
                    Toast.makeText(this@AltaEstudiante,"Registrado \uD83D\uDE1C\uD83E\uDD13", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this@AltaEstudiante,"Ingresar Apellido",Toast.LENGTH_LONG).show()
                }
        }else{
                Toast.makeText(this@AltaEstudiante,"Ingresar Nombre",Toast.LENGTH_LONG).show()
            }

        }



        // Control de fechas
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtFecha.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                val mes= (monthOfYear + 1)
                var cadena =""
                if(mes in 1..9){
                    cadena =("0")+mes.toString()
                }else{
                    cadena = mes.toString()
                }
                edtFecha.setText("" + year + "-" + cadena.toString() + "-" + dayOfMonth)
            }, year, month, day)
            dpd.show()

        }
        studentsDb.studentsGetAll()

    } // fin del oncreate

    } // final de la clase












