package com.example.rungekutta

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.runge_kutta.*

class RungeKutta : AppCompatActivity(){
    lateinit var funcion: String
    lateinit var x0: String
    lateinit var y0: String
    lateinit var h: String
    lateinit var n: String
    lateinit var opc: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se llama el layout de crear_usuario
        setContentView(R.layout.runge_kutta)

        btnOrden2.setOnClickListener(){
            opc = "2"
            sendValues(opc)
        }// Botón para 2do Orden

        btnOrden4.setOnClickListener(){
            opc = "4"
            sendValues(opc)
        }// Botón para 3er Orden

    }//función crear


    private fun validate(): Boolean = editTextFuncion.text.toString().trim().isNotEmpty()
            && editTextX.text.toString().trim().isNotEmpty() && editTextY.text.toString().trim().isNotEmpty()
            && editTextH.text.toString().trim().isNotEmpty() && editTextN.text.toString().trim().isNotEmpty()


    private fun sendValues(opc: String){
        if(validate()){
            if(opc == "2"){
                funcion = editTextFuncion.text.toString().trim()
                x0 = editTextX.text.toString().trim()
                y0 = editTextY.text.toString().trim()
                h = editTextH.text.toString().trim()
                n = editTextN.text.toString().trim()
                this.opc = "2"

                sendMainActivity(funcion, x0, y0, h, n, opc)
                finish()
            } else {
                funcion = editTextFuncion.text.toString().trim()
                x0 = editTextX.text.toString().trim()
                y0 = editTextY.text.toString().trim()
                h = editTextH.text.toString().trim()
                n = editTextN.text.toString().trim()
                this.opc = "4"

                sendMainActivity(funcion, x0, y0, h, n, opc)
                finish()
            }
        }
        else {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
        }// crear cuenta

    }

    private fun sendMainActivity(funcion:String, x0:String, y0:String, h:String, n:String, opc:String){
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("funcion",funcion)
            putExtra("x0", x0)
            putExtra("y0", y0)
            putExtra("h", h)
            putExtra("n", n)
            putExtra("opc", opc)
        }
        startActivity(homeIntent)

    }// Función que envia los datos a la función MainActivity y cambia de pesataña

}