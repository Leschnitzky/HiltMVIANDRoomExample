package com.example.roomhiltexample.ui.viewmodels

import androidx.lifecycle.*

import com.example.roomhiltexample.model.User
import com.example.roomhiltexample.repository.UserRepository
import com.example.roomhiltexample.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
    ) : ViewModel() {
        private val _dataState : MutableLiveData<DataState<List<User>>> = MutableLiveData()
        val dataState: LiveData<DataState<List<User>>>
            get() =_dataState

        fun emitIntent(mainIntent: MainIntent){
            viewModelScope.launch {
                when (mainIntent) {
                    is MainIntent.GetUserEvents -> {
                        userRepository.getUsers()
                            .onEach { dataState ->
                                _dataState.value = dataState
                            }
                            .launchIn(viewModelScope)
                    }
                    is MainIntent.None -> {

                    }
                }
            }
        }

    fun emitIntentWithUser(mainIntent: MainIntent, user: User){
        viewModelScope.launch {
            when (mainIntent) {
                is MainIntent.AddUser -> {
                    userRepository.addUser(user).launchIn(viewModelScope)
                }
                is MainIntent.None -> {

                }
            }
        }
    }
}

sealed class MainIntent {
    object AddUser: MainIntent()
    object GetUserEvents: MainIntent()
    object None: MainIntent()
}