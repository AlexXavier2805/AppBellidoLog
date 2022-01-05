package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.AlumnoEntity
import com.example.bellidolog.model.interactor.PerfilInteractor
import com.example.bellidolog.presenter.impl.PerfilPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilInteractorImpl(private val presenter: PerfilPresenterImpl) : PerfilInteractor {

    override fun updateFoto(token: String, alumnoEntity: AlumnoEntity) {
        RetrofitService.getIntance().updateAlumn("Bearer $token", alumnoEntity).enqueue(object :
            Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.code() == 200){
                    presenter.successful("Foto actualizada")
                }
                else{
                    println(response.code())
                    presenter.error("Error al actualizar la foto")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                presenter.error("Error de api")
            }
        })
    }

}