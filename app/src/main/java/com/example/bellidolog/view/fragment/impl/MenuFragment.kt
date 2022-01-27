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
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        //Obteniendo el objeto matricula de los argumento del fragment
        val matricula = requireArguments().getSerializable("matricula") as MatriculaEntity

        /*
        if(matricula.alumno.foto.isNullOrBlank()){
            Toast.makeText(binding.root.context, "no tiene foto", Toast.LENGTH_SHORT).show()
        }*/

        //Guardando el id y nombre de la alumna en dos variables inmutables
        val alumnaId = "ID: ${matricula.matriculaId}"
        val nombreAlumna = "${matricula.alumno.nombre} ${matricula.alumno.apellidoPaterno} ${matricula.alumno.apellidoMaterno}"

        //Asignando texto al xml con las variables alumnaId y nombreAlumna
        binding.tvIdAlumna.text = alumnaId
        binding.tvNombre.text = nombreAlumna
        if(!matricula.alumno.foto.isNullOrBlank()){
            val bitmapFoto = loadPhoto(matricula.alumno.foto)
            binding.ivAlumna.setImageBitmap(bitmapFoto)
        }else{
            binding.ivAlumna.setImageResource(R.drawable.alumna)
        }

        //-- TERMINADO
        //Poniendo el escucha los eventos clic de mis botones del xml
        binding.btnCurso.setOnClickListener {
            //Guardando la instancia de CursoFragment y el id de grado para el metodo
            val instanciaCursoFragment = CursoFragmentView()
            val gradoId = matricula.seccion.grado.gradoId
            //Ejecutando el Metodo openCursoFragment pasando una instancia de CursoFragment y el id de grado del objeto matricula
            openCursoFragment(instanciaCursoFragment,gradoId)
        }
        //-- TERMINADO
        binding.btnNota.setOnClickListener {
            val instanciaNotaFragment = NotaFragmentView()
            val matriculaId = matricula.matriculaId
            openNotaFragment(instanciaNotaFragment,matriculaId)
        }

        //-- TERMINADO
        binding.btnLink.setOnClickListener { abrirLink()}

        binding.btnConfiguracion.setOnClickListener {
            val instaciaConfiguracionFragment = ConfiguracionFragmentView()
            val usuario = matricula.alumno.usuario
            openConfiguration(instaciaConfiguracionFragment,usuario)
        }

        //-- TERMINADO
        binding.btnCambiarFoto.setOnClickListener {
            val instaciaPerfilFragment = PerfilFragmentView()
            val alumno = matricula.alumno
            openFotoFragment(instaciaPerfilFragment,alumno)
        }

        return binding.root
    }

    private fun abrirLink() {
        val url = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW,url))
    }

    /*private fun openFragment(fragment: Fragment) {
        moveToFragment(fragment)
    }*/

    //Metodo openCursoFragment que sirve para guardar informacion de un fragment
    private fun openCursoFragment(fragment: Fragment, gradoId: Int){
        //creando objeto bundle para guardar el id de grado
        val bundle = Bundle()
        //Guardando el id con putInt
        bundle.putInt("id",gradoId)
        //Guardando el bundle en los argumentos de fragment
        fragment.arguments = bundle
        //Ejecutando el metodo moveToFragment para remplazar el este fragment por el CursoFragment
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


    //Metodo moveToFragment que sirve para abrir otro fragmento en la activity actual
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