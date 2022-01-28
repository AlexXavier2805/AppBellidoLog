package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.JwtRequest
import com.example.bellidolog.model.entity.JwtResponse
import com.example.bellidolog.model.entity.MatriculaEntity
import com.example.bellidolog.model.interactor.impl.MainInteractorImpl
import com.example.bellidolog.presenter.MainPresenter
import com.example.bellidolog.view.activity.MainActivityView

class MainPresenterImpl(private val mainActivityView: MainActivityView) : MainPresenter {

    private val interactor = MainInteractorImpl(this)

    override fun autenticar(jwtRequest: JwtRequest) {
        interactor.autenticar(jwtRequest)
    }

    override fun buscarMatricula(token: String, username: String) {
        interactor.buscarMatricula(token, username)
    }

    override fun obtenerToken(jwtResponse: JwtResponse?) {
        mainActivityView.obtenerToken(jwtResponse)
    }

    override fun tokenError(error: String) {
        mainActivityView.tokenError(error)
    }

    override fun obtenerMatricula(matriculaEntity: MatriculaEntity?) {
        mainActivityView.obtenerMatricula(matriculaEntity)
    }

    override fun matriculaError(error: String) {
        mainActivityView.matriculaError(error)
    }

}