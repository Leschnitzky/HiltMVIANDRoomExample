package com.example.roomhiltexample.ui.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*

import com.example.roomhiltexample.model.User
import com.example.roomhiltexample.repository.UserRepository
import com.example.roomhiltexample.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {
        private val _totalUserDataState : MutableLiveData<DataState<List<User>>> = MutableLiveData()
        val totalUserDataState: LiveData<DataState<List<User>>>
            get() = _totalUserDataState

        private val _addedToDb : MutableLiveData<DataState<Long>> = MutableLiveData()
        val addedToDb: LiveData<DataState<Long> >
            get() = _addedToDb

        fun emitIntent(mainIntent: MainIntent){
            viewModelScope.launch {
                when (mainIntent) {
                    is MainIntent.GetUserEvents -> {
                        userRepository.getUsers()
                            .onEach { dataState ->
                                _totalUserDataState.value = dataState
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
                    userRepository.addUser(user)
                            .onEach { addedLine -> _addedToDb.value = addedLine }
                            .launchIn(viewModelScope)
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