package com.cricbuzzapp.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricbuzz.domain.core.UseCaseResponse
import com.cricbuzz.domain.model.GenericDomain
import com.cricbuzz.domain.model.RestaurantDomain
import com.cricbuzz.domain.resource.Resource
import com.cricbuzz.domain.usecase.FetchRestaurantsUseCase
import com.cricbuzz.domain.usecase.InsertDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchRestaurantsUseCase: FetchRestaurantsUseCase,
    private val insertDataUseCase: InsertDataUseCase
): ViewModel() {

    private var navigator: MainNavigator? = null

    var searchText by mutableStateOf("")
    private val searchTextFlow by lazy { MutableStateFlow("") }
    private val fetchRestaurantState: MutableState<Resource<List<RestaurantDomain>>> by lazy {
        mutableStateOf(Resource.initial())
    }

    val restaurantList by lazy {
        mutableStateListOf<RestaurantDomain?>()
    }


    private val insertDataState: MutableState<Resource<GenericDomain>> by lazy {
        mutableStateOf(Resource.initial())
    }

    init {
        initDebounce()
        insertData()
    }

    fun updateSearchText(text: String){
        searchText = text
    }

    fun onSearchTextChanged(searchString: String) {
        updateSearchText(searchString)
        searchTextFlow.value = searchString
    }

    private fun initDebounce() {
        searchTextFlow.debounce(2000).onEach {
            if (it.isNotEmpty() && it.length > 1) {
                fetchRestaurants(it)
            }
        }.launchIn(viewModelScope)
    }

    private fun insertData() {
        insertDataState.value = Resource.loading()
        insertDataUseCase.invoke(viewModelScope, insertDataCallback())
    }

    private fun insertDataCallback() = object : UseCaseResponse<GenericDomain> {
        override suspend fun onSuccess(result: GenericDomain) {

        }
    }

    private fun fetchRestaurants(it: String) {
        fetchRestaurantState.value = Resource.loading()
        fetchRestaurantsUseCase.invoke(viewModelScope, fetchAddressCallback(), FetchRestaurantsUseCase.Params(it))
    }

    private fun fetchAddressCallback() = object : UseCaseResponse<List<RestaurantDomain>> {
        override suspend fun onSuccess(result: List<RestaurantDomain>) {
            handleSuccess(result)
        }
    }

    private fun handleSuccess(restaurantDomainList: List<RestaurantDomain>) {
        restaurantList.clear()
        val localList: ArrayList<RestaurantDomain> = arrayListOf()
        restaurantDomainList.onEach {
            localList.add(it)
        }
        restaurantList.addAll(localList)
        getNavigator()?.onSearchResultSuccess()
    }

    fun setNavigator(nav: MainNavigator) {
        navigator = nav
    }

    private fun getNavigator() = navigator
}