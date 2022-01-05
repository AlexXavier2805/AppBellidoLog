package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.HorarioEntity
import com.example.bellidolog.model.interactor.impl.HorarioInteractorImpl
import com.example.bellidolog.presenter.HorarioPresenter
import com.example.bellidolog.view.fragment.HorarioFragment

class HorarioPresenterImpl(private val fragment: HorarioFragment) : HorarioPresenter {

    private val interactor = HorarioInteractorImpl(this)

    override fun buscarHorario(token: String, seccionId: Int) {
        interactor.buscarHorario(token,seccionId)
    }

    override fun obtenerHorario(lista: List<HorarioEntity>?) {
        fragment.obtenerHorario(lista)
    }

    override fun horarioError(error: String) {
        fragment.horarioError(error)
    }
}