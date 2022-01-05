package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.NotaEntity
import com.example.bellidolog.model.interactor.NotaInteractor
import com.example.bellidolog.presenter.impl.NotaPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaInteractorImpl(private val notaPresenterImpl: NotaPresenterImpl) : NotaInteractor {

    override fun buscarNotas(token: String, matriculaId: Int) {
        RetrofitService.getIntance().findCalificationById("Bearer $token", matriculaId).enqueue(object :
            Callback<List<NotaEntity>>{
            override fun onResponse(
                call: Call<List<NotaEntity>>,
                response: Response<List<NotaEntity>>
            ) {
                if(!response.body().isNullOrEmpty()){
                    notaPresenterImpl.obtenerNotas(response.body() as List<NotaEntity>)
                }else{
                    notaPresenterImpl.obtenerNotas(null)
                }

            }

            override fun onFailure(call: Call<List<NotaEntity>>, t: Throwable) {
                notaPresenterImpl.notaError("Error")
            }
        })
    }


}