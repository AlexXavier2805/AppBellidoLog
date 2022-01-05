package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.HorarioEntity
import com.example.bellidolog.model.interactor.HorarioInteractor
import com.example.bellidolog.presenter.impl.HorarioPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HorarioInteractorImpl(private val presenter: HorarioPresenterImpl) : HorarioInteractor {

    override fun buscarHorario(token: String, seccionId: Int) {
        RetrofitService.getIntance().findScheduleById("Bearer $token", seccionId).enqueue(object :
            Callback<List<HorarioEntity>> {
            override fun onResponse(
                call: Call<List<HorarioEntity>>,
                response: Response<List<HorarioEntity>>
            ) {
                if(!response.body().isNullOrEmpty()){
                    presenter.obtenerHorario(response.body())
                }else{
                    presenter.obtenerHorario(null)
                }
            }

            override fun onFailure(call: Call<List<HorarioEntity>>, t: Throwable) {
                presenter.horarioError("Error")
            }
        })
    }
}