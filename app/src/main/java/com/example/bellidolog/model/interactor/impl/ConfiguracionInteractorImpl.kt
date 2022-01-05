package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.UsuarioEntity
import com.example.bellidolog.model.interactor.ConfiguracionInteractor
import com.example.bellidolog.presenter.impl.ConfiguracionPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfiguracionInteractorImpl(private val presenter: ConfiguracionPresenterImpl) : ConfiguracionInteractor{

    override fun updatePassword(token: String, user: UsuarioEntity) {
        RetrofitService.getIntance().updateUser("Bearer $token", user).enqueue( object :
            Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.code()==200){
                    presenter.successful("Actualizacion Exitosa")
                }else{
                    presenter.error("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                presenter.error("Error de Api")
            }
        })
    }
}