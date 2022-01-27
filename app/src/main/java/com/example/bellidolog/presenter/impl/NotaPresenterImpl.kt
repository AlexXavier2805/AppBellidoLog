package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.NotaEntity
import com.example.bellidolog.model.interactor.impl.NotaInteractorImpl
import com.example.bellidolog.presenter.NotaPresenter
import com.example.bellidolog.view.fragment.impl.NotaFragmentView

class NotaPresenterImpl(private val notaFragmentView: NotaFragmentView) : NotaPresenter {

    val interactor = NotaInteractorImpl(this)
    override fun buscarNotas(token: String, matriculaId: Int) {
        interactor.buscarNotas(token, matriculaId)
    }

    override fun obtenerNotas(lista: List<NotaEntity>?) {
        notaFragmentView.obtenerNotas(lista)
    }

    override fun notaError(error: String) {
        notaFragmentView.notaError(error)
    }


}