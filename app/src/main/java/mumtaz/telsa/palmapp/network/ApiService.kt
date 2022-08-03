package mumtaz.telsa.palmapp.network

import mumtaz.telsa.palmapp.model.GetAllUserResponseItem
import mumtaz.telsa.palmapp.model.PostUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("register")
    suspend fun getUser(
        @Query("email") email : String
    ): List<GetAllUserResponseItem>

    @POST("register")
    suspend fun addUsers(
        @Body user : PostUserResponse
    ): Call<GetAllUserResponseItem>

    @PUT ("register/{id}")
    suspend fun updateUser(
        @Body user : PostUserResponse, @Path("id") id:String
    ) : GetAllUserResponseItem
}