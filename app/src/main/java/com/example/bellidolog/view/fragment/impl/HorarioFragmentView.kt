package com.example.bellidolog.view.fragment.impl

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bellidolog.view.adapter.HorarioAdapter
import com.example.bellidolog.databinding.FragmentHorarioBinding
import com.example.bellidolog.model.entity.HorarioEntity
import com.example.bellidolog.presenter.impl.HorarioPresenterImpl
import com.example.bellidolog.view.fragment.IHorarioView

class HorarioFragmentView : Fragment(), IHorarioView {

    private lateinit var binding: FragmentHorarioBinding

    private val presenter = HorarioPresenterImpl(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHorarioBinding.inflate(inflater, container, false)

        binding.pbHorario.visibility = View.VISIBLE

        binding.rvHorario.setHasFixedSize(true)
        binding.rvHorario.layoutManager = LinearLayoutManager(this.requireContext())

        val seccionId = this.requireArguments().getInt("id")

        buscarHorario(seccionId)

        return binding.root
    }

    private fun buscarHorario(seccionId: Int){
        val preferences = this.requireActivity().getSharedPreferences("MyPrefe",Context.MODE_PRIVATE)
        val token = preferences.getString("TOKEN","")
        presenter.buscarHorario(token!!,seccionId)
    }

    override fun obtenerHorario(lista: List<HorarioEntity>?) {
        binding.pbHorario.visibility = View.INVISIBLE
        if(lista != null){
            val adapter = HorarioAdapter(lista)
            binding.rvHorario.adapter = adapter
        }else{
            Toast.makeText(this.requireContext(), "No tiene horarios Asignados", Toast.LENGTH_SHORT).show()
        }
    }

    override fun horarioError(error: String) {
        Toast.makeText(this.requireContext(), "Error de api", Toast.LENGTH_SHORT).show()
    }
}