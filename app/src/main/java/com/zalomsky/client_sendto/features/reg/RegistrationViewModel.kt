package com.zalomsky.client_sendto.features.reg

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.User
import com.zalomsky.client_sendto.domain.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
): ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createNewUser(user: User, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                registrationUseCase(user = user)
                onSuccess()
            } catch (e: Exception){
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}