package com.example.android_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.android_db.Data.StudentsDB
import com.example.android_db.Data.StudentsEntity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.itemAltaEstudiante->{
                val intent = Intent(this@MainActivity,AltaEstudiante::class.java)
                startActivity(intent)
            }

            R.id.itemListaEstudiante->{
                val intent = Intent(this@MainActivity,VerEstudiantes :: class.java)
                startActivity(intent)
            }

            R.id.itemEditarEstudiante->{
                val intent = Intent(this@MainActivity,Lista_Estudiantes_Edit::class.java)
                startActivity(intent)
            }

            R.id.itemEliminarEstudiante->{
                val intent = Intent(this@MainActivity,EliminarEstudiante::class.java)
                startActivity(intent)
            }
        }

        return true
    }

}
