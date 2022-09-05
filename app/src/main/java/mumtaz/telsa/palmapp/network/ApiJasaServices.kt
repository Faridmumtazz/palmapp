package mumtaz.telsa.palmapp.network

import mumtaz.telsa.palmapp.model.GetAllJasaResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiJasaServices {

    @GET("jasa")
    fun getAllJasa(): Call<List<GetAllJasaResponseItem>>

    companion object{
        var apiFilmServices: ApiJasaServices? = null
        fun getInstance() : ApiJasaServices{
            if (apiFilmServices == null) {
                val baseUrl = "https://62e67ad7de23e263792cfaed.mockapi.io/"
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiFilmServices = retrofit.create(ApiJasaServices::class.java)
            }
            return apiFilmServices!!
        }
    }
}