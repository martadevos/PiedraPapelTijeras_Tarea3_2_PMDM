package com.example.piedrapapeltijeras_tarea3_2_pmdm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
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
    private var fragmentoMaquina: FragmentContainerView? = null

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
        fragmentoMaquina = findViewById(R.id.mostrarFragmentoMaquina)

        btnReiniciarPuntuacion?.setOnClickListener(this)
    }

    /**
     * Al pulsar el boton btnReiniciarPuntuacion, pone las variables de puntuacion y los correspondientes textos a 0
     * @param p0 vista correspondiente al botón pulsado
     */
    override fun onClick(p0: View?) {
        //Al hacer click en el botón de reiniciar puntuación, pone las puntuaciones de jugador y máquina a 0
        if (p0?.id == btnReiniciarPuntuacion?.id) {
            txtPuntuacionMaquina?.text = "0"
            txtPuntuacionJugador?.text = "0"
            puntuacionMaquina = 0
            puntuacionJugador = 0
        }
    }

    /**
     * Llama al métdo eleccionJugador y utiliza un handler para llamar a los siguientes métodos tras x segundos:
     * eleccionMaquina tras 2 segundos
     * mostrarGanador tras 4 segundos
     * preguntarSiSeguir tras 6 segundos
     * @param id entero correspondiente al botón pulsado
     */
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

    /**
     * Asigna la image view imgViewEleccionJugador a la variable imgJugador,
     * asigna el linear layout botonesEleccion a la variable botonesEleccion,
     * asigna a la variable resultadoJugador el id pasado por parámetro,
     * asigna la imagen correspondiente a resultadoJugador a imgJugador con un array de enteros y
     * pone visibilidad de botonesEleccion a gone y imgJugador a visible
     * @param id entero correspondiente al botón pulsado
     */
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

    /**
     * Asigna la image view imgViewEleccionMaquina a la variable imgMaquina,
     * asigna un resultado con un random entre 1 y 5 a la variable resultadoMaquina
     * y asigna la imagen correspondiente a resultadoMaquina a imgMaquina con un array de enteros
     */
    private fun eleccionMaquina() {
        imgMaquina = findViewById(R.id.imgViewEleccionMaquina)
        //asigna a la eleccion de la máquina un número entre 1 y 5 incluidos ambos
        resultadoMaquina = Random.nextInt(1, 5)

        //Muestra la imagen correspondiente a la elección del jugador
        imgMaquina?.setImageResource(arrayImagenes[resultadoMaquina!! - 1])
    }

    /**
     * Llama al método seleccionarGanador, muestra un dialogo con el resultado de la ronda
     * y actualiza las puntuaciones; al pulsar el botón ok cierra la alerta
     */
    private fun mostrarGanador() {
        seleccionarGanador()
        AlertDialog.Builder(this)
            .setTitle("Resultado:")
            .setMessage(cadenaGanador)
            .setPositiveButton("Ok", null)
            .create()
            .show()
        txtPuntuacionJugador?.text = puntuacionJugador.toString()
        txtPuntuacionMaquina?.text = puntuacionMaquina.toString()
    }

    /**
     * Rellena la acadena que se mostrará en el dialogo de ganador dependiendo de la elección del
     * jugador y la máquina a traves de un when
     */
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

    /**
     * Muestra un dialogo que pregunta si desea continuar, si se pulsa el botón sí,
     * pone visibles los botones de elección del jugador, visbilidad gone de la imagen
     * de la eleccion del jugador y pone el interrogante en la imagen de elección de la máquina;
     * si se pulsa no, cierra la aplicación
     */
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
            .setNegativeButton("No") { _, _ ->
                Log.d("Dialog", "---------------- No ----------------")
                finish()
            }
            .create()
            .show()
    }
}