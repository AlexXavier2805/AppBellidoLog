package com.example.bellidolog.view.fragment.impl

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bellidolog.view.adapter.NotaAdapter
import com.example.bellidolog.databinding.FragmentNotaBinding
import com.example.bellidolog.model.entity.NotaEntity
import com.example.bellidolog.presenter.impl.NotaPresenterImpl
import com.example.bellidolog.view.fragment.INotaView

class NotaFragmentView : Fragment(), INotaView {

    private lateinit var binding: FragmentNotaBinding

    private val presenter = NotaPresenterImpl(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotaBinding.inflate(inflater, container, false)

        binding.pbNotas.visibility = View.VISIBLE

        binding.rvNota.setHasFixedSize(true)
        binding.rvNota.layoutManager = LinearLayoutManager(this.requireContext())

        val matriculaId = this.requireArguments().getInt("id")

        buscarNotas(matriculaId)

        return binding.root
    }

    private fun buscarNotas(matriculaId: Int){
        val preferences = this.requireActivity().getSharedPreferences("MyPrefe", Context.MODE_PRIVATE)
        val token = preferences.getString("TOKEN","")
        presenter.buscarNotas(token!!,matriculaId)
    }

    override fun obtenerNotas(lista: List<NotaEntity>?) {
        binding.pbNotas.visibility = View.INVISIBLE
        if(lista != null){
            val adapter = NotaAdapter(lista)
            binding.rvNota.adapter = adapter
            println(lista)
        }else{
            Toast.makeText(this.requireContext(), "No tiene notas Asignadas", Toast.LENGTH_SHORT).show()
        }
    }

    override fun notaError(notasError: String) {
        Toast.makeText(this.requireContext(), notasError, Toast.LENGTH_SHORT).show()
    }

}