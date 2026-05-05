package com.example.drugdose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrugViewModel(private val repository: DrugRepository) : ViewModel() {
    private val _allDrugs = MutableStateFlow<List<PrincipioAttivo>>(emptyList())
    
    private val _filteredDrugs = MutableStateFlow<List<PrincipioAttivo>>(emptyList())
    val filteredDrugs: StateFlow<List<PrincipioAttivo>> = _filteredDrugs.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        val drugs = repository.getDrugs()
        _allDrugs.value = drugs
        _filteredDrugs.value = drugs
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        _filteredDrugs.value = if (newQuery.isBlank()) {
            _allDrugs.value
        } else {
            _allDrugs.value.filter {
                it.nome.contains(newQuery, ignoreCase = true) ||
                it.descrizione.contains(newQuery, ignoreCase = true) ||
                it.farmaci_commerciali.any { farmaco -> 
                    farmaco.nome.contains(newQuery, ignoreCase = true) 
                }
            }
        }
    }
}
