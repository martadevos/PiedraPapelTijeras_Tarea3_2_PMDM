package com.example.piedrapapeltijeras_tarea3_2_pmdm

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class JugadorFragment : Fragment() {
    private var listener: OnFragmentActionsListener? = null
    private var btnScissors: ImageButton? = null
    private var btnRock: ImageButton? = null
    private var btnPaper: ImageButton? = null
    private var btnLizard: ImageButton? = null
    private var btnSpock: ImageButton? = null
    private var root: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_jugador, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnScissors = root?.findViewById(R.id.imgBtnScissors)
        btnRock = root?.findViewById(R.id.imgBtnRock)
        btnPaper = root?.findViewById(R.id.imgBtnPaper)
        btnLizard = root?.findViewById(R.id.imgBtnLizard)
        btnSpock = root?.findViewById(R.id.imgBtnSpock)

        btnScissors?.setOnClickListener { listener?.onClickFragmentButton(1) }
        btnRock?.setOnClickListener { listener?.onClickFragmentButton(2) }
        btnPaper?.setOnClickListener { listener?.onClickFragmentButton(3) }
        btnLizard?.setOnClickListener { listener?.onClickFragmentButton(4) }
        btnSpock?.setOnClickListener { listener?.onClickFragmentButton(5) }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}