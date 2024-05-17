package com.zalomsky.client_sendto.features.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?>
        get() = _user

    fun getUser() {
/*        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = getUserUseCase()
                _user.value = user
            } catch (e: Exception) {
                Log.e("AccountScreenViewModel", "Exception during request -> ${e.localizedMessage}")
            }
        }*/
    }
}