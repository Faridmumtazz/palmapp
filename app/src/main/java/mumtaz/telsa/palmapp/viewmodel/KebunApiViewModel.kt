package mumtaz.telsa.palmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mumtaz.telsa.palmapp.data.utils.KebunApiRepository
import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KebunApiViewModel(private val repository: KebunApiRepository) : ViewModel(){

    val liveDataKebunApi = MutableLiveData<List<GetAllKebunResponseItem>>()

    fun getAllKebunApi(){
        val response = repository.getAllKebunApi()
        response.enqueue(object : Callback<List<GetAllKebunResponseItem>>{
            override fun onResponse(
                call: Call<List<GetAllKebunResponseItem>>,
                response: Response<List<GetAllKebunResponseItem>>
            ) {
                liveDataKebunApi.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GetAllKebunResponseItem>>, t: Throwable) {
                //nothing
            }

        })
    }

}