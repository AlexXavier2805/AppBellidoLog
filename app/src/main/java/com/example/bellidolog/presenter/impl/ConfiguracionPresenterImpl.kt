package com.example.bellidolog.presenter.impl

import com.example.bellidolog.model.entity.UsuarioEntity
import com.example.bellidolog.model.interactor.impl.ConfiguracionInteractorImpl
import com.example.bellidolog.presenter.ConfiguracionPresenter
import com.example.bellidolog.view.fragment.impl.ConfiguracionFragmentView

class ConfiguracionPresenterImpl(private val configuracionFragmentView: ConfiguracionFragmentView) : ConfiguracionPresenter{

    private val interactor = ConfiguracionInteractorImpl(this)

    override fun updatePassword(token: String, user: UsuarioEntity) {
        interactor.updatePassword(token, user)
    }

    override fun successful(msgSuccessful: String) {
        configuracionFragmentView.successful(msgSuccessful)
    }

    override fun error(error: String) {
        configuracionFragmentView.error(error)
    }

}