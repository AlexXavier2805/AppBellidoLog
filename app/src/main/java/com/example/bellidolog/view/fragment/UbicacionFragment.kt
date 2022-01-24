package com.example.bellidolog.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bellidolog.R
import com.example.bellidolog.databinding.FragmentCursoBinding
import com.example.bellidolog.databinding.FragmentUbicacionBinding


class UbicacionFragment : Fragment() {

    private lateinit var binding: FragmentUbicacionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUbicacionBinding.inflate(inflater, container, false)

        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.flMapa,MapaFragment()).commit()

        return inflater.inflate(R.layout.fragment_ubicacion, container, false)
    }

}