package com.tunalex.uesanapp.presentacion.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tunalex.uesanapp.data.local.AppDatabase
import com.tunalex.uesanapp.data.local.CountryEntity
import com.tunalex.uesanapp.data.model.CountryModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).countryDao()

    val favorites: StateFlow<List<CountryEntity>> = dao.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavorite(country: CountryModel, isCurrentlyFavorite: Boolean) {
        viewModelScope.launch {
            val entity = CountryEntity(country.name, country.ranking, country.imageUrl)
            if (isCurrentlyFavorite) dao.delete(entity) else dao.insert(entity)
        }
    }
}
