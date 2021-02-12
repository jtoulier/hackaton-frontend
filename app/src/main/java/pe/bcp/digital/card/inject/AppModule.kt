package pe.bcp.digital.card.inject

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import pe.bcp.digital.card.data.repository.UserRepository
import pe.bcp.digital.card.data.repository.UserRepositoryImpl
import pe.bcp.digital.card.feature.login.LoginViewModel
import pe.bcp.digital.card.data.network.Network

val appModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single { Network.client }
    viewModel { LoginViewModel(get()) }
}

