package com.app.integracioncomunitariaapp.ui.postulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.integracioncomunitariaapp.network.RetrofitClient
import com.app.integracioncomunitariaapp.repository.PostulationRepository

class PostulationsListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostulationsListViewModel::class.java)) {
            val repository = PostulationRepository(RetrofitClient.instance)
            @Suppress("UNCHECKED_CAST")
            return PostulationsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}