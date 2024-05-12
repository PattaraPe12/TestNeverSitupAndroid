package pattara.phuteeka.neversitup.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pattara.phuteeka.neversitup.data.remote.model.GetDepartmentResponse
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse
import pattara.phuteeka.neversitup.domain.DepartmentUseCase
import pattara.phuteeka.neversitup.domain.ImageUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: DepartmentUseCase,
    private val imgUseCase:ImageUseCase
) : ViewModel() {
    private val _listDepartment = MutableLiveData<List<GetDepartmentResponse>>()
    val listDepartment: LiveData<List<GetDepartmentResponse>> get() = _listDepartment

    private val _listProduct = MutableLiveData<List<GetProductResponse>>()
    val listProduct: LiveData<List<GetProductResponse>> get() = _listProduct

    private val _depIdSelect = MutableLiveData<String>()
    val depIdSelect: LiveData<String> get() = _depIdSelect

    fun getDepartment(){
//        viewModelScope.launch {
//            useCase.execute()
//        }
        CoroutineScope(Dispatchers.IO).launch {
            val response = useCase.execute()
            _listDepartment.postValue(response)
        }
    }

    fun getProduct(depId:String){
        _depIdSelect.postValue(depId)
        CoroutineScope(Dispatchers.IO).launch {
            val response = useCase.execute(depId)
            _listProduct.postValue(response)
        }
    }

    fun getDepartmentById():GetDepartmentResponse?{
        listDepartment.value?.find {it.id?:"" == depIdSelect.value  }?.let {
            return it
        }.also {
            return null
        }
    }

    fun getImageUrl(url:String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = imgUseCase.execute(url)
//            Log.d("DEBUG","ImageUseCaseImpl code : ${response?.code()}")
//            Log.d("DEBUG","ImageUseCaseImpl body : ${Gson().toJson(response?.body())}")
//            Log.d("DEBUG","ImageUseCaseImpl raw : ${Gson().toJson(response?.raw())}")
//            Log.d("DEBUG","ImageUseCaseImpl headers : ${Gson().toJson(response?.headers())}")
//            Log.d("DEBUG","MainViewModel url : ${response}")
//            _listDepartment.postValue(response)
        }
    }
}