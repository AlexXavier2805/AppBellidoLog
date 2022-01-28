package com.example.bellidolog.view.fragment.impl

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bellidolog.R
import com.example.bellidolog.databinding.FragmentMenuBinding
import com.example.bellidolog.model.entity.AlumnoEntity
import com.example.bellidolog.model.entity.MatriculaEntity
import com.example.bellidolog.model.entity.UsuarioEntity

class MenuFragment : Fragment() {

    private val link = "https://www.facebook.com/AuladelCRT1/"

    private lateinit var binding: FragmentMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        val matricula = requireArguments().getSerializable("matricula") as MatriculaEntity

        val alumnaId = "ID: ${matricula.matriculaId}"
        val nombreAlumna = "${matricula.alumno.nombre} ${matricula.alumno.apellidoPaterno} ${matricula.alumno.apellidoMaterno}"

        binding.tvIdAlumna.text = alumnaId
        binding.tvNombre.text = nombreAlumna
        if(!matricula.alumno.foto.isNullOrBlank()){
            val bitmapFoto = loadPhoto(matricula.alumno.foto)
            binding.ivAlumna.setImageBitmap(bitmapFoto)
        }else{
            binding.ivAlumna.setImageResource(R.drawable.alumna)
        }

        binding.btnCurso.setOnClickListener {
            val instanciaCursoFragment = CursoFragmentView()
            val gradoId = matricula.seccion.grado.gradoId
            openCursoFragment(instanciaCursoFragment,gradoId)
        }

        binding.btnNota.setOnClickListener {
            val instanciaNotaFragment = NotaFragmentView()
            val matriculaId = matricula.matriculaId
            openNotaFragment(instanciaNotaFragment,matriculaId)
        }

        binding.btnLink.setOnClickListener { openLink()}

        binding.btnConfiguracion.setOnClickListener {
            val instaciaConfiguracionFragment = ConfiguracionFragmentView()
            val usuario = matricula.alumno.usuario
            openConfiguration(instaciaConfiguracionFragment,usuario)
        }

        binding.btnCambiarFoto.setOnClickListener {
            val instaciaPerfilFragment = PerfilFragmentView()
            val alumno = matricula.alumno
            openFotoFragment(instaciaPerfilFragment,alumno)
        }

        return binding.root
    }

    private fun openLink() {
        val url = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW,url))
    }

    private fun openCursoFragment(fragment: Fragment, gradoId: Int){
        val bundle = Bundle()
        bundle.putInt("id",gradoId)
        fragment.arguments = bundle
        moveToFragment(fragment)
    }

    private fun openNotaFragment(fragment: Fragment, matriculaId: Int){
        val bundle = Bundle()
        bundle.putInt("id",matriculaId)
        fragment.arguments = bundle
        moveToFragment(fragment)
    }

    private fun openFotoFragment(fragment: Fragment, alumnoEntity: AlumnoEntity){
        val bundle = Bundle()
        bundle.putSerializable("alumno",alumnoEntity)
        fragment.arguments = bundle
        moveToFragment(fragment)
    }

    private fun openConfiguration(fragment: Fragment, usuarioEntity: UsuarioEntity){
        val bundle = Bundle()
        bundle.putSerializable("usuario",usuarioEntity)
        fragment.arguments = bundle
        moveToFragment(fragment)
    }

    private fun moveToFragment(fragment: Fragment){
        val transaction =  this.requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContenedor,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun loadPhoto(foto: String): Bitmap {
        val fotoByte: ByteArray = Base64.decode(
            foto.substring(foto.indexOf(",") + 1),
            Base64.DEFAULT
        )
        return BitmapFactory.decodeByteArray(fotoByte, 0, fotoByte.size)
    }
}