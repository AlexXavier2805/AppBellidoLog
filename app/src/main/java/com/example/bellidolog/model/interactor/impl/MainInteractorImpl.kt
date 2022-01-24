package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.JwtRequest
import com.example.bellidolog.model.entity.JwtResponse
import com.example.bellidolog.model.entity.MatriculaEntity
import com.example.bellidolog.model.interactor.MainInteractor
import com.example.bellidolog.presenter.impl.MainPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractorImpl(private val presenter: MainPresenterImpl) : MainInteractor {

    override fun autenticar(jwtRequest: JwtRequest) {
        RetrofitService.getIntance().authenticate(jwtRequest).enqueue(object :
            Callback<JwtResponse>{
            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                if(response.code() == 200){
                    presenter.obtenerToken(response.body())
                } else {
                    presenter.obtenerToken(null)
                }
            }

            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                presenter.tokenError("Error api authenticacion")
                t.printStackTrace()
            }
        })
    }

    override fun buscarMatricula(token: String, username: String) {
        RetrofitService.getIntance().findByUsername("Bearer $token",username).enqueue(object : Callback<MatriculaEntity>{
            override fun onResponse(
                call: Call<MatriculaEntity>,
                response: Response<MatriculaEntity>
            ) {
                println(response.code())
                if(response.code() == 200){
                    presenter.obtenerMatricula(response.body())
                }else{
                    presenter.obtenerMatricula(null)
                }
            }

            override fun onFailure(call: Call<MatriculaEntity>, t: Throwable) {
                presenter.matriculaError("Error")
                t.printStackTrace()
            }
        })
    }


}