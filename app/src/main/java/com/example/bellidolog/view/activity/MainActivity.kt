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

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenterImpl(this)

    private lateinit var bindind: ActivityMainBinding

    private lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        bindind.btnLog.setOnClickListener { autenticar() }
    }

    private fun autenticar(){
        //obteniendo usuario y contraseña del xml
        val usuario = bindind.edtUser.text.toString()
        val password = bindind.edtPassword.text.toString()

        //validando que el usuario y contraseña no estén vacios
        if(usuario.isNotBlank() && password.isNotBlank()){

            //Creando un objeto del data class JwtRequest
            val jwtRequest = JwtRequest(usuario,password)

            //Ejecutando el metodo autenticar de presenter pasando como argumento el objeto de JwtRequest
            presenter.autenticar(jwtRequest)
        }else{
            Toast.makeText(this@MainActivity, "Faltan completar Datos", Toast.LENGTH_SHORT).show()
        }

    }

    //Respuesta del método autenticar
    override fun obtenerToken(jwtResponse: JwtResponse?) {
        //validar que el objeto de JwtResponse de respuesta no sea nulo
        if(jwtResponse != null){
            //obteniendo el usuario del xml
            val usuario = bindind.edtUser.text.toString()
            //guardando el token en una variable de ambito global
            token = jwtResponse.jwttoken
            //ejecutando el método busarMatricula del presenter
            presenter.buscarMatricula(token,usuario)
        }else{
            Toast.makeText(this@MainActivity, "Usuario Invalido", Toast.LENGTH_SHORT).show()
        }
    }

    //Respuesta de error del método autenticar
    override fun tokenError(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        bindind.edtUser.setText("")
        bindind.edtPassword.setText("")
    }

    //Respuesta del metodo buscarMatricula
    override fun obtenerMatricula(matriculaEntity: MatriculaEntity?) {
        //Validar que el objeto MatriculaEntity no sea nulo
        if(matriculaEntity != null){
            //Crear un objeto sharedPreference
            val preferences = getSharedPreferences("MyPrefe", Context.MODE_PRIVATE)

            //Almacenar el valor de la variable token en el Shared Preference
            preferences.edit().putString("TOKEN",token).apply()

            //Crear un objeto Bundle para guardar un objeto matricula con una key
            val bundle = Bundle()
            bundle.putSerializable("matricula",matriculaEntity)

            //Crear un objeto de intent para pasar a otra actividad
            val intent = Intent(this@MainActivity,MenuActivity::class.java)
            //Pasar como put Extra el bundle que almacena nuestro objeto matricula
            intent.putExtras(bundle)
            //Iniciar la actididad con el objeto intent
            startActivity(intent)
        }else{
            Toast.makeText(this@MainActivity, "Usuario Invalido", Toast.LENGTH_SHORT).show()
        }
    }

    //Respuesta de error del método buscarMatricula
    override fun matriculaError(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }


}