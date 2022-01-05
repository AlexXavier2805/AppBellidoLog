package com.example.bellidolog.model.interactor.impl

import com.example.bellidolog.model.entity.CursoEntity
import com.example.bellidolog.model.interactor.CursoInteractor
import com.example.bellidolog.presenter.impl.CursoPresenterImpl
import com.example.bellidolog.red.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CursoInteractorImpl(private val cursoPresenterImpl: CursoPresenterImpl) : CursoInteractor {

    override fun buscarCursos(token: String, gradoId: Int) {
        RetrofitService.getIntance().findCoursesByGrade("Bearer $token",gradoId).enqueue(object :
            Callback<List<CursoEntity>> {
            override fun onResponse(
                call: Call<List<CursoEntity>>,
                response: Response<List<CursoEntity>>
            ) {
                val lista = response.body()

                if(!lista.isNullOrEmpty()){
                    cursoPresenterImpl.obtenerListaCursos(lista)
                }else{
                    cursoPresenterImpl.obtenerListaCursos(null)
                }
            }

            override fun onFailure(call: Call<List<CursoEntity>>, t: Throwable) {
                cursoPresenterImpl.listaCursosError("Error")
            }
        })
    }
}