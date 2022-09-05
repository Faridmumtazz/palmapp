package mumtaz.telsa.palmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mumtaz.telsa.palmapp.data.utils.JasaApiRepository
import mumtaz.telsa.palmapp.data.utils.KebunApiRepository
import mumtaz.telsa.palmapp.model.GetAllJasaResponseItem
import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JasaApiViewModel (private val repository: JasaApiRepository) : ViewModel(){

    val liveDataJasaApi = MutableLiveData<List<GetAllJasaResponseItem>>()

    fun getAllJasaApi(){
        val response = repository.getAllJasaApi()
        response.enqueue(object : Callback<List<GetAllJasaResponseItem>> {
            override fun onResponse(
                call: Call<List<GetAllJasaResponseItem>>,
                response: Response<List<GetAllJasaResponseItem>>
            ) {
                liveDataJasaApi.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GetAllJasaResponseItem>>, t: Throwable) {
                //nothing
            }

        })
    }

}