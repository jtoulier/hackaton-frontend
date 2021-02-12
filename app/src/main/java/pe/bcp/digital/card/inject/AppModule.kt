package pe.bcp.digital.card.inject

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import pe.bcp.digital.card.data.model.UserSession
import pe.bcp.digital.card.data.repository.UserRepository
import pe.bcp.digital.card.data.repository.UserRepositoryImpl
import pe.bcp.digital.card.feature.login.LoginViewModel
import pe.bcp.digital.card.data.network.Network
import pe.bcp.digital.card.data.repository.CardRepository
import pe.bcp.digital.card.data.repository.CardRepositoryImpl
import pe.bcp.digital.card.feature.addcard.AddCardViewModel
import pe.bcp.digital.card.feature.home.HomeViewModel

val appModule = module {

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<CardRepository> { CardRepositoryImpl(get(), get()) }
    single { Network.client }

    single { UserSession() }

    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddCardViewModel(get()) }
}

