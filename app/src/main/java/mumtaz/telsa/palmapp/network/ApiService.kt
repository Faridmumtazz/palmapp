package mumtaz.telsa.palmapp.network

import mumtaz.telsa.palmapp.model.GetAllUserResponseItem
import mumtaz.telsa.palmapp.model.PostUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("register")
    fun getAllUsers() : Call<List<GetAllUserResponseItem>>

    @POST("register")
    fun addUsers(
        @Body user : PostUserResponse
    ): Call<GetAllUserResponseItem>
}