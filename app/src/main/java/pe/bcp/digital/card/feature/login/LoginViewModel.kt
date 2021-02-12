package pe.bcp.digital.card.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.bcp.digital.card.data.repository.UserRepository
import pe.bcp.digital.card.util.Event
import pe.bcp.digital.card.util.Result

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<String>>()
    val navigateToHome : LiveData<Event<String>>
        get() = _navigateToHome

    private val _progress = MutableLiveData<Event<Boolean>>()
    val progress : LiveData<Event<Boolean>>
        get() = _progress

    private val _message = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = _message


    fun login(document: String, pwd: String) {
        viewModelScope.launch {
            _progress.value = Event(true)
            val result = withContext(Dispatchers.IO){
                userRepository.login(document, pwd)
            }
            _progress.value = Event(false)

            when(result){
                is Result.Success -> { _navigateToHome.value = Event("nav")}
                is Result.Error -> { _message.value = Event("Usuario Incorrecto")}
            }

        }
    }

}

