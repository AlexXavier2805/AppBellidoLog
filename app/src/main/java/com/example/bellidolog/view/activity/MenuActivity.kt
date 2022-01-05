package com.example.bellidolog.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bellidolog.R
import com.example.bellidolog.databinding.ActivityMenuBinding
import com.example.bellidolog.model.entity.MatriculaEntity
import com.example.bellidolog.view.fragment.HorarioFragment
import com.example.bellidolog.view.fragment.MenuFragment
import com.example.bellidolog.view.fragment.UbicacionFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    private lateinit var matricula:MatriculaEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //obteniendo el objeto matricula del serializableExtra
        matricula = intent.getSerializableExtra("matricula") as MatriculaEntity

        
        openFragment(MenuFragment())

        Toast.makeText(this, "Bienvenida: ${matricula.alumno.nombre} ${matricula.alumno.apellidoPaterno} ${matricula.alumno.apellidoMaterno}", Toast.LENGTH_SHORT).show()

        binding.bnvMenu.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.nav_menu ->{
                    val fragment = MenuFragment()
                    openFragment(fragment)
                    true
                }
                R.id.nav_schedule ->{
                    val fragment = HorarioFragment()
                    val seccionId = matricula.seccion.seccionId
                    openScheduleFragment(fragment, seccionId)
                    true
                }
                R.id.nav_ubi ->{
                    val fragment = UbicacionFragment()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }

        binding.btnLogOut.setOnClickListener {
            val shared = getSharedPreferences("MyPrefe",Context.MODE_PRIVATE).edit()
            shared.clear().apply()

            this.finish()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putSerializable("matricula",matricula)
        fragment.arguments = bundle
        moveToFragment(fragment)
    }

    private fun openScheduleFragment(fragment: Fragment, seccionId: Int){
        val bundle = Bundle()

        bundle.putInt("id",seccionId)

        fragment.arguments = bundle

        moveToFragment(fragment)
    }

    //Metodo moveToFragment que sirve para abrir otro fragmento en la activity actual
    private fun moveToFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContenedor,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}