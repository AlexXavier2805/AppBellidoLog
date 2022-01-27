package com.example.bellidolog.view.fragment.impl

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bellidolog.R
import com.example.bellidolog.databinding.FragmentPerfilBinding
import com.example.bellidolog.model.entity.AlumnoEntity
import com.example.bellidolog.presenter.impl.PerfilPresenterImpl
import com.example.bellidolog.view.fragment.IPerfilView
import java.io.ByteArrayOutputStream

class PerfilFragmentView : Fragment(), IPerfilView {

    private val PICK_IMAGE = 1

    private var estadoSeleccion = false

    private var imageUri:Uri? = null

    private lateinit var binding: FragmentPerfilBinding

    private val presenter = PerfilPresenterImpl(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater,container,false)

        binding.btnSeleccionar.setOnClickListener { openGallery() }

        val alumnoEntity = requireArguments().getSerializable("alumno") as AlumnoEntity

        if(!alumnoEntity.foto.isNullOrBlank()){
            val bitmapFoto = loadPhoto(alumnoEntity.foto)
            binding.ivFotoPerfil.setImageBitmap(bitmapFoto)
        }else{
            binding.ivFotoPerfil.setImageResource(R.drawable.alumna)
        }

        binding.btnActualizarFoto.setOnClickListener {
            updatePhoto(alumnoEntity)
        }

        return binding.root
    }

    private fun updatePhoto(alumnoEntity: AlumnoEntity) {
        if(estadoSeleccion){
            estadoSeleccion = false

            val drawable = binding.ivFotoPerfil.drawable as BitmapDrawable
            val fotoAlumna = drawableToString(drawable)
            alumnoEntity.foto = fotoAlumna

            val preferences = this.requireContext().getSharedPreferences("MyPrefe", Context.MODE_PRIVATE)
            val token = preferences.getString("TOKEN","")

            presenter.updateFoto(token!!, alumnoEntity)
        }else{
            Toast.makeText(this.requireContext(), "No seleccionó ninguna foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        estadoSeleccion = true
        startActivityForResult(
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI),
            PICK_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data!!.data
            binding.ivFotoPerfil.setImageURI(imageUri)
            Toast.makeText(this.requireContext(),"Imagen Seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadPhoto(foto: String): Bitmap {
        val fotoByte: ByteArray = Base64.decode(
            foto.substring(foto.indexOf(",") + 1),
            Base64.DEFAULT
        )
        return BitmapFactory.decodeByteArray(fotoByte, 0, fotoByte.size)
    }

    private fun drawableToString(drawable: BitmapDrawable): String{
        val bitmap = drawable.bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
        val imagen = stream.toByteArray()
        return Base64.encodeToString(imagen,Base64.DEFAULT)
    }

    override fun successful(msgSuccessful: String) {
        Toast.makeText(this.requireContext(), msgSuccessful, Toast.LENGTH_SHORT).show()
    }

    override fun error(msgError: String) {
        Toast.makeText(this.requireContext(), msgError, Toast.LENGTH_SHORT).show()
    }

}