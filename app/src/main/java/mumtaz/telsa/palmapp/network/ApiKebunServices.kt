package mumtaz.telsa.palmapp.network

import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiKebunServices {

    @GET("perkebunan")
    fun getAllKebun(): Call<List<GetAllKebunResponseItem>>

    companion object{
        var apiFilmServices: ApiKebunServices? = null
        fun getInstance() : ApiKebunServices{
            if (apiFilmServices == null) {
                val baseUrl = "https://62e67ad7de23e263792cfaed.mockapi.io/"
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiFilmServices = retrofit.create(ApiKebunServices::class.java)
            }
            return apiFilmServices!!
        }
    }
}