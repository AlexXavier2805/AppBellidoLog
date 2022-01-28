package com.example.bellidolog.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bellidolog.databinding.ActivityMainBinding
import com.example.bellidolog.model.entity.JwtRequest
import com.example.bellidolog.model.entity.JwtResponse
import com.example.bellidolog.model.entity.MatriculaEntity
import com.example.bellidolog.presenter.impl.MainPresenterImpl

class MainActivityView : AppCompatActivity(), IMainView {

    private val presenter = MainPresenterImpl(this)

    private lateinit var bindind: ActivityMainBinding

    private lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        bindind.btnLog.setOnClickListener { authentication() }
    }

    private fun authentication(){
        val usuario = bindind.edtUser.text.toString()
        val password = bindind.edtPassword.text.toString()

        if(usuario.isNotBlank() && password.isNotBlank()){
            val jwtRequest = JwtRequest(usuario,password)
            presenter.autenticar(jwtRequest)
        }else{
            Toast.makeText(this@MainActivityView, "Faltan completar Datos", Toast.LENGTH_SHORT).show()
        }

    }

    override fun obtenerToken(jwtResponse: JwtResponse?) {
        if(jwtResponse != null){
            val usuario = bindind.edtUser.text.toString()
            token = jwtResponse.jwttoken
            presenter.buscarMatricula(token,usuario)
        }else{
            Toast.makeText(this@MainActivityView, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun tokenError(error: String) {
        Toast.makeText(this@MainActivityView, error, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        bindind.edtUser.setText("")
        bindind.edtPassword.setText("")
    }

    override fun obtenerMatricula(matriculaEntity: MatriculaEntity?) {
        if(matriculaEntity != null){
            val preferences = getSharedPreferences("MyPrefe", Context.MODE_PRIVATE)

            preferences.edit().putString("TOKEN",token).apply()

            val bundle = Bundle()
            bundle.putSerializable("matricula",matriculaEntity)

            val intent = Intent(this@MainActivityView,MenuActivityView::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }else{
            Toast.makeText(this@MainActivityView, "Usuario Invalido", Toast.LENGTH_SHORT).show()
        }
    }

    override fun matriculaError(error: String) {
        Toast.makeText(this@MainActivityView, error, Toast.LENGTH_SHORT).show()
    }
}