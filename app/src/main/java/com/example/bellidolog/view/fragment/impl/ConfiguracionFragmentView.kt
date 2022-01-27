package com.example.bellidolog.view.fragment.impl

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bellidolog.databinding.FragmentConfiguracionBinding
import com.example.bellidolog.model.entity.UsuarioEntity
import com.example.bellidolog.presenter.impl.ConfiguracionPresenterImpl
import com.example.bellidolog.view.fragment.IConfiguracionView


class ConfiguracionFragmentView : Fragment(), IConfiguracionView {

    private lateinit var binding: FragmentConfiguracionBinding

    private val presenter = ConfiguracionPresenterImpl(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false)

        val usuarioEntity = requireArguments().getSerializable("usuario") as UsuarioEntity

        binding.btnCambiarPassword.setOnClickListener { updatePassword(usuarioEntity) }

        return binding.root
    }

    private fun updatePassword(usuarioEntity: UsuarioEntity) {
        val newPassword = binding.etdPassword1.text.toString()
        val confirmPassword = binding.edtPassword2.text.toString()
        if(newPassword == confirmPassword){
            usuarioEntity.contrasenia = newPassword
            val preferences = this.requireContext().getSharedPreferences("MyPrefe",Context.MODE_PRIVATE)
            val token = preferences.getString("TOKEN","")
            presenter.updatePassword(token!!,usuarioEntity)
        } else {
            Toast.makeText(this.requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }
    }

    override fun successful(msgSuccessful: String) {
        Toast.makeText(this.requireContext(), msgSuccessful, Toast.LENGTH_SHORT).show()
    }

    override fun error(error: String) {
        Toast.makeText(this.requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}