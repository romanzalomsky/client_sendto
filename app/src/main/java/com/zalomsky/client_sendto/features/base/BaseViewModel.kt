package com.zalomsky.client_sendto.features.base

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.Client
import com.zalomsky.client_sendto.domain.usecase.AddClientUseCase
import com.zalomsky.client_sendto.domain.usecase.GetClientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase,
    private val addClientUseCase: AddClientUseCase
) : ViewModel() {

    private val _client = MutableLiveData<Client>()
    val client: LiveData<Client>
        get() = _client

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>>
        get() = _clients

    fun getClientsList() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val clients = getClientsUseCase()
                _clients.postValue(clients)
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun addClient(client: Client, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                addClientUseCase(client = client)
                onSuccess()
            } catch (e: Exception){
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}