package com.example.rungekutta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Double.parseDouble
import java.lang.Math.sqrt
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = intent?.extras
        /*
        * Variables que recibiran los valores enviados de la anterior activity
        * */
        val funcion = bundle?.getString("funcion")
        val x0 = bundle?.getString("x0")
        val y0 = bundle?.getString("y0")
        val h0 = bundle?.getString("h")
        val n0 = bundle?.getString("n")
        val opc0 = bundle?.getString("opc")

        /*
        *  Declaración e inicialización de variables
        * */
        var opSuma = false; var opResta = false; var opDiv = false;
        var opMult = false; var opRaiz = false; var opPoten = false
        //var longitud= funcion?.length
        var numeric = true
        var num = 1.0
        var delimitador = " "
        var i=0

        /*
        * Array que guarda la función que haya ingresado el usuario
        * */
        val parts: List<String>? = funcion?.split(delimitador)


        /*
        * Condicional que valida si hay elementos en List<String>
        * */
        if (parts != null) {
            for(i in parts.indices){
                try {
                    num = parseDouble(parts?.get(0))
                } catch (e: NumberFormatException) {
                    numeric = false
                }
                if(parts?.get(i) == "+"){
                    // Toast.makeText(this, "Entró en suma", Toast.LENGTH_LONG).show()
                    opSuma = true
                } else if(parts?.get(i) == "-"){
                    // Toast.makeText(this, "Entró en Resta", Toast.LENGTH_LONG).show()
                    opResta = true
                } else if(parts?.get(i) == "*"){
                    Toast.makeText(this, "Entró a Multi", Toast.LENGTH_LONG).show()
                    opMult = true
                } else if(parts?.get(i) == "/"){
                    // Toast.makeText(this, "Entró a División", Toast.LENGTH_LONG).show()
                    opDiv = true
                } else if(parts?.get(i) == "√"){
                    // Toast.makeText(this, "Entró a Raíz", Toast.LENGTH_LONG).show()
                    opRaiz = true
                } else if(parts?.get(i) == "^"){
                    // Toast.makeText(this, "Entró a Potencia", Toast.LENGTH_LONG).show()
                    opPoten = true
                } else if(numeric){
                    // Toast.makeText(this, "Es un número", Toast.LENGTH_LONG).show()

                }
            }
        }



        /*
        * Declaración de variables que se usarán para evaluar kunge Kutta
        * */
        var k1= 0.0; var k2 = 0.0; var k3 = 0.0; var k4 = 0.0;
        var px= 0.0; var py = 0.0; var resul = 0.0

        /*
        * Casteo de valores enviados por el usuario
        * */
        var x = x0?.toDouble()
        var y = y0?.toDouble()
        var h = h0?.toDouble()
        var n = n0?.toDouble()
        var opc = opc0?.toInt()
        var resultados: ArrayList<String> = ArrayList()

        /*
        * Condicional que valida si las variables son diferentes de 0
        * */
        if (x != null && y != null && h !=null && n != null) {
            if(opSuma){
                x = num * x
                /*
                * Condicional que valida si el usuario quiere resolver la ecuación en
                * segundo orden o en cuarto orden
                * */
                if(opc == 4){
                    /*
                    * Resolución de la ecuación en 4to orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x + y!!

                        //calcular el valor de k2
                        px = x + h / 2.0;
                        py = y + h / 2.0 * k1;
                        k2 = px + py;

                        //calcular el valor de k3
                        px = x + h / 2;
                        py = y + h / 2 * k2;
                        k3 = px + py;

                        //calcular el valor de k4
                        px = x + h;
                        py = y + h * k3;
                        k4 = px + py

                        //Resultado
                        resul = y + (((1.0 / 6.0) * (k1 + (2.0 * k2) + (2.0 * k3) + k4)) * h)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                } else {
                    /*
                    * Resolución de la ecuación en 2do orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x + y!!

                        //Calcula los puntos para evaluar a la función
                        px = x + h
                        py = y + k1 * h

                        //calcular el valor de k2
                        k2 = px + py;

                        //Resultado
                        resul = y + (h / 2) * (k1 + k2)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                }
            } else if(opResta){
                x = num * x
                /*
                * Condicional que valida si el usuario quiere resolver la ecuación en
                * segundo orden o en cuarto orden
                * */
                if(opc == 4){
                    /*
                    * Resolución de la ecuación en 4to orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x - y!!

                        //calcular el valor de k2
                        px = x + h / 2.0;
                        py = y + h / 2.0 * k1;
                        k2 = px - py;

                        //calcular el valor de k3
                        px = x + h / 2;
                        py = y + h / 2 * k2;
                        k3 = px - py;

                        //calcular el valor de k4
                        px = x + h;
                        py = y + h * k3;
                        k4 = px - py

                        //Resultado
                        resul = y + (((1.0 / 6.0) * (k1 + (2.0 * k2) + (2.0 * k3) + k4)) * h)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                } else {
                    /*
                    * Resolución de la ecuación en 2do orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x - y!!

                        //Calcula los puntos para evaluar a la función
                        px = x + h
                        py = y + k1 * h

                        //calcular el valor de k2
                        k2 = px - py;

                        //Resultado
                        resul = y + (h / 2) * (k1 + k2)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                }

            } else if(opMult){
                x = num * x
                /*
                * Condicional que valida si el usuario quiere resolver la ecuación en
                * segundo orden o en cuarto orden
                * */
                if(opc == 4){
                    /*
                    * Resolución de la ecuación en 4to orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x * y!!

                        //calcular el valor de k2
                        px = x + h / 2.0;
                        py = y + h / 2.0 * k1;
                        k2 = px * py;

                        //calcular el valor de k3
                        px = x + h / 2;
                        py = y + h / 2 * k2;
                        k3 = px * py;

                        //calcular el valor de k4
                        px = x + h;
                        py = y + h * k3;
                        k4 = px * py

                        //Resultado
                        resul = y + (((1.0 / 6.0) * (k1 + (2.0 * k2) + (2.0 * k3) + k4)) * h)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                } else {
                    /*
                    * Resolución de la ecuación en 2do orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x * y!!

                        //Calcula los puntos para evaluar a la función
                        px = x + h
                        py = y + k1 * h

                        //calcular el valor de k2
                        k2 = px * py;

                        //Resultado
                        resul = y + (h / 2) * (k1 + k2)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                }

            } else if(opDiv){
                x = num * x
                /*
                * Condicional que valida si el usuario quiere resolver la ecuación en
                * segundo orden o en cuarto orden
                * */
                if(opc == 4){
                    /*
                    * Resolución de la ecuación en 4to orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x / y!!

                        //calcular el valor de k2
                        px = x + h / 2.0;
                        py = y + h / 2.0 * k1;
                        k2 = px / py;

                        //calcular el valor de k3
                        px = x + h / 2;
                        py = y + h / 2 * k2;
                        k3 = px / py;

                        //calcular el valor de k4
                        px = x + h;
                        py = y + h * k3;
                        k4 = px / py

                        //Resultado
                        resul = y + (((1.0 / 6.0) * (k1 + (2.0 * k2) + (2.0 * k3) + k4)) * h)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                } else {
                    /*
                    * Resolución de la ecuación en 2do orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x / y!!

                        //Calcula los puntos para evaluar a la función
                        px = x + h
                        py = y + k1 * h

                        //calcular el valor de k2
                        k2 = px / py;

                        //Resultado
                        resul = y + (h / 2) * (k1 + k2)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                }

            } else if(opRaiz){
                x = num * x
                Toast.makeText(this, "numero: "+ x, Toast.LENGTH_LONG).show()
                /*
                * Condicional que valida si el usuario quiere resolver la ecuación en
                * segundo orden o en cuarto orden
                * */
                if(opc == 4){
                    /*
                    * Resolución de la ecuación en 4to orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x * sqrt(y!!)

                        //calcular el valor de k2
                        px = x + h / 2.0;
                        py = y + h / 2.0 * k1;
                        k2 = px * sqrt(py);

                        //calcular el valor de k3
                        px = x + h / 2;
                        py = y + h / 2 * k2;
                        k3 = px * sqrt(py);

                        //calcular el valor de k4
                        px = x + h;
                        py = y + h * k3;
                        k4 = px * sqrt(py)

                        //Resultado
                        resul = y + (((1.0 / 6.0) * (k1 + (2.0 * k2) + (2.0 * k3) + k4)) * h)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                } else {
                    /*
                    * Resolución de la ecuación en 2do orden
                    * */
                    n = n - h
                    while(x < n){
                        //calcular el valor de k1
                        k1 = x * sqrt(y!!)

                        //Calcula los puntos para evaluar a la función
                        px = x + h
                        py = y + k1 * h

                        //calcular el valor de k2
                        k2 = px * sqrt(py);

                        //Resultado
                        resul = y + (h / 2) * (k1 + k2)

                        resultados.add(resul.toString())

                        //Actualizar X,Y
                        y = resul;
                        x += h;
                    }
                }
            } else if(opPoten){

                x = (num * x)
                x = x.pow(y)

            }
        }
        //Toast.makeText(this, "tamaño: "+ longitud, Toast.LENGTH_LONG).show()
        if(resultados != null){
            //initRecycler()
            rvResul.layoutManager = LinearLayoutManager(this)
            val adapter = ResultadosAdapter(resultados)
            rvResul.adapter = adapter
            //Toast.makeText(this, "Entró", Toast.LENGTH_LONG).show()
        } else {
            //Toast.makeText(this, "No entró", Toast.LENGTH_LONG).show()
        }


        btnSalir.setOnClickListener() {
            startActivity(Intent(this, RungeKutta::class.java))
            finish()
        }// botón para entrar
    }
/*
    fun initRecycler(){
        rvResul.layoutManager = LinearLayoutManager(this)
        val adapter = ResultadosAdapter(resultados)
        rvResul.adapter = adapter
    }
*/

}




