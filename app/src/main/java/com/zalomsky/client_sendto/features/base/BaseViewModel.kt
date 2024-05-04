package com.zalomsky.client_sendto.features.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.Client
import com.zalomsky.client_sendto.domain.usecase.GetClientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase,
) : ViewModel() {

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>>
        get() = _clients

    fun getClientsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getClientsUseCase.invoke().let {
                _clients.postValue(it)
            }
        }
    }
}