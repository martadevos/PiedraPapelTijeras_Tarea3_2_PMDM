package com.example.piedrapapeltijeras_tarea3_2_pmdm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener, OnFragmentActionsListener {
    private var btnReiniciarPuntuacion: Button? = null
    private var txtPuntuacionJugador: TextView? = null
    private var txtPuntuacionMaquina: TextView? = null
    private var resultadoJugador: Int? = null
    private var resultadoMaquina: Int? = null
    private var cadenaGanador: String? = null
    private var puntuacionJugador: Int = 0
    private var puntuacionMaquina: Int = 0

    private var imgJugador: ImageView? = null
    private var botonesEleccion: LinearLayout? = null


    private var imgMaquina: ImageView? = null
    private var arrayImagenes: Array<Int> = arrayOf(
        R.drawable.scissors,
        R.drawable.rock,
        R.drawable.paper,
        R.drawable.lizard,
        R.drawable.spock
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReiniciarPuntuacion = findViewById(R.id.btnReiniciarPuntuacion)
        txtPuntuacionJugador = findViewById(R.id.txtPuntucacionJugador)
        txtPuntuacionMaquina = findViewById(R.id.txtPuntuacioMaquina)

        btnReiniciarPuntuacion?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //Al hacer click en el botón de reiniciar puntuación, pone las puntuaciones de jugador y máquina a 0
        if (p0?.id == btnReiniciarPuntuacion?.id) {
            txtPuntuacionMaquina?.text = "0"
            txtPuntuacionJugador?.text = "0"
            puntuacionMaquina = 0
            puntuacionJugador = 0
        }
    }

    override fun onClickFragmentButton(id: Int) {
        eleccionJugador(id)
        val handler = Handler(Looper.myLooper()!!)
        //Ejecuta la eleccion de la maquina tras 2 segundos
        handler.postDelayed({ eleccionMaquina() }, 2 * 1000)
        //Muestra una alerta con el ganador de la ronda tras 2 segundos
        handler.postDelayed({ mostrarGanador() }, 4 * 1000)
        //Muestra una alerta para preguntar si desea seguir jugando tras 2 segundos
        handler.postDelayed({ preguntarSiSeguir() }, 6 * 1000)

    }

    private fun eleccionJugador(id: Int) {
        imgJugador = findViewById(R.id.imgViewEleccionJugador)
        botonesEleccion = findViewById(R.id.botonesEleccion)
        //Asigna a la eleccion del jugador el id correspondiente al botón pulsado(un número entre 1 y 5)
        resultadoJugador = id
        //Muestra la imagen correspondiente a la elección del jugador
        imgJugador?.setImageResource(arrayImagenes[resultadoJugador!! - 1])
        //Oculta los botones de elección y hace visible la imagen
        botonesEleccion?.visibility = View.GONE
        imgJugador?.visibility = View.VISIBLE
    }

    private fun eleccionMaquina() {
        imgMaquina = findViewById(R.id.imgViewEleccionMaquina)
        //asigna a la eleccion de la máquina un número entre 1 y 5 incluidos ambos
        resultadoMaquina = Random.nextInt(1, 5)

        //Muestra la imagen correspondiente a la elección del jugador
        imgMaquina?.setImageResource(arrayImagenes[resultadoMaquina!! - 1])
    }

    private fun mostrarGanador() {
        seleccionarGanador()
        AlertDialog.Builder(this)
            .setTitle("Resultado:")
            .setMessage(cadenaGanador)
            .setPositiveButton("Ok") { _, _ ->
                Log.d("Dialog", "---------------- Ok ----------------")
            }
            .create()
            .show()
        txtPuntuacionJugador?.text = puntuacionJugador.toString()
        txtPuntuacionMaquina?.text = puntuacionMaquina.toString()
    }

    private fun seleccionarGanador() {

        if (resultadoJugador == resultadoMaquina) cadenaGanador = "Empate :/"
        else when (resultadoJugador) {
            1 -> {
                if (resultadoMaquina == 3 || resultadoMaquina == 4) {
                    cadenaGanador = "Has ganado :)"
                    puntuacionJugador++
                } else {
                    cadenaGanador = "Has perdido :("
                    puntuacionMaquina++
                }
            }
            2 -> {
                if (resultadoMaquina == 1 || resultadoMaquina == 4) {
                    cadenaGanador = "Has ganado :)"
                    puntuacionJugador++
                } else {
                    cadenaGanador = "Has perdido :("
                    puntuacionMaquina++
                }
            }
            3 -> {
                if (resultadoMaquina == 2 || resultadoMaquina == 5) {
                    cadenaGanador = "Has ganado :)"
                    puntuacionJugador++
                } else {
                    cadenaGanador = "Has perdido :("
                    puntuacionMaquina++
                }
            }
            4 -> {
                if (resultadoMaquina == 3 || resultadoMaquina == 5) {
                    cadenaGanador = "Has ganado :)"
                    puntuacionJugador++
                } else {
                    cadenaGanador = "Has perdido :("
                    puntuacionMaquina++
                }
            }
            5 -> {
                if (resultadoMaquina == 1 || resultadoMaquina == 2) {
                    cadenaGanador = "Has ganado :)"
                    puntuacionJugador++
                } else {
                    cadenaGanador = "Has perdido :("
                    puntuacionMaquina++
                }
            }
            else -> cadenaGanador = "Ha habido un fallo con las elecciones ToT"
        }
    }

    private fun preguntarSiSeguir() {
        AlertDialog.Builder(this)
            .setTitle("Seguir")
            .setMessage("¿Desea seguir jugando?")
            .setPositiveButton("Sí") { _, _ ->
                Log.d("Dialog", "---------------- Sí ----------------")
                //Vuelve a mostrar los botones de elección y pone la imagen del interrogante en la máquina
                imgJugador?.visibility = View.GONE
                botonesEleccion?.visibility = View.VISIBLE
                imgMaquina?.setImageResource(R.drawable.question_mark)
            }
            .setNegativeButton("No", null)
            .create()
            .show()
    }
}