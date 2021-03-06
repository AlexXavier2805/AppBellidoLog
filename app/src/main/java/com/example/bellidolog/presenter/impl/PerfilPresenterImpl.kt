package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.AlumnoEntity
import com.example.bellidolog.model.interactor.impl.PerfilInteractorImpl
import com.example.bellidolog.presenter.PerfilPresenter
import com.example.bellidolog.view.fragment.impl.PerfilFragmentView

class PerfilPresenterImpl(private val perfilFragmentView: PerfilFragmentView) :PerfilPresenter {

    private val interactor = PerfilInteractorImpl(this)

    override fun updateFoto(token: String, alumnoEntity: AlumnoEntity) {
        interactor.updateFoto(token, alumnoEntity)
    }

    override fun successful(msgSuccessful: String) {
        perfilFragmentView.successful(msgSuccessful)
    }

    override fun error(msgError: String) {
        perfilFragmentView.error(msgError)
    }
}