package com.example.bellidolog.view.fragment.impl

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bellidolog.view.adapter.CursoAdapter
import com.example.bellidolog.databinding.FragmentCursoBinding
import com.example.bellidolog.model.entity.CursoEntity
import com.example.bellidolog.presenter.impl.CursoPresenterImpl
import com.example.bellidolog.view.fragment.ICursoView


class CursoFragmentView : Fragment(), ICursoView {

    private lateinit var binding: FragmentCursoBinding

    private val presenter = CursoPresenterImpl(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCursoBinding.inflate(inflater, container, false)

        binding.pbCurso.visibility = View.VISIBLE

        binding.rvCursos.setHasFixedSize(true)
        binding.rvCursos.layoutManager = LinearLayoutManager(this.requireContext())

        val gradoId = requireArguments().getInt("id")

        buscarCursos(gradoId)

        return binding.root
    }

    private fun buscarCursos(cursoId:Int){
        val preferences = this.requireActivity().getSharedPreferences("MyPrefe", Context.MODE_PRIVATE)
        val token = preferences.getString("TOKEN","")
        presenter.buscarCursos(token!!,cursoId)
    }

    override fun obtenerListaCursos(lista: List<CursoEntity>?) {
        binding.pbCurso.visibility = View.INVISIBLE
        if(lista != null){
            val adapter = CursoAdapter(lista)
            binding.rvCursos.adapter = adapter
        }else{
            Toast.makeText(this.requireContext(), "No tiene cursos asignados", Toast.LENGTH_SHORT).show()
        }
    }

    override fun listaCursosError(error: String) {
        Toast.makeText(this.requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }

}