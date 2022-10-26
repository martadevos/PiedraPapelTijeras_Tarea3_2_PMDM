package com.example.piedrapapeltijeras_tarea3_2_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener, OnFragmentActionsListener {
    var btnReiniciarPuntuacion: Button? = null
    var txtPuntuacionJugador: TextView? = null
    var txtPuntuacionMaquina: TextView? = null
    var resultadoJugador: Int? = null
    var resultadoMaquina: Int? = null

    var imgJugador: ImageView? = null
    var botonesEleccion: LinearLayout? = null


    var imgMaquina: ImageView? = null
    var arrayImagenes: Array<Int> = arrayOf(
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
        txtPuntuacionJugador = findViewById(R.id.txtPuntuacioMaquina)

        btnReiniciarPuntuacion?.setOnClickListener(this)
        imgJugador?.visibility = View.GONE
        botonesEleccion?.visibility = View.VISIBLE
    }

    private fun reiniciarPuntuaciones() {

        // set the computer and player score to 0
        txtPuntuacionMaquina?.text = "0"
        txtPuntuacionJugador?.text = "0"

    }

    override fun onClick(p0: View?) {
        if (p0?.id == btnReiniciarPuntuacion?.id) reiniciarPuntuaciones()
    }

    override fun onClickFragmentButton(id: Int) {
        imgJugador = findViewById(R.id.imgViewEleccionJugador)
        botonesEleccion = findViewById(R.id.botonesEleccion)

        resultadoJugador = id
        imgJugador?.setImageResource(arrayImagenes[resultadoJugador!! - 1])
        botonesEleccion?.visibility = View.GONE
        imgJugador?.visibility = View.VISIBLE

        imgMaquina = findViewById(R.id.imgViewEleccionMaquina)
        resultadoMaquina = Random.nextInt(1, 5)
        imgMaquina?.setImageResource(arrayImagenes[resultadoMaquina!! - 1])
    }
}