package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.CursoEntity
import com.example.bellidolog.model.interactor.impl.CursoInteractorImpl
import com.example.bellidolog.presenter.CursoPresenter
import com.example.bellidolog.view.fragment.impl.CursoFragmentView

class CursoPresenterImpl(private val cursoFragmentView: CursoFragmentView) : CursoPresenter {

    private val interactor = CursoInteractorImpl(this)

    override fun buscarCursos(token: String, gradoId: Int) {
        interactor.buscarCursos(token,gradoId)
    }

    override fun obtenerListaCursos(lista: List<CursoEntity>?) {
        cursoFragmentView.obtenerListaCursos(lista)
    }

    override fun listaCursosError(error: String) {
        cursoFragmentView.listaCursosError(error)
    }
}