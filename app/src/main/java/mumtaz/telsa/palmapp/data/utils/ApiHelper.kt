package mumtaz.telsa.palmapp.data.utils

import mumtaz.telsa.palmapp.model.PostUserResponse
import mumtaz.telsa.palmapp.network.ApiService
import javax.inject.Inject
import javax.inject.Named

class ApiHelper @Inject constructor(
    @Named("User")
    private val apiServiceUser: ApiService
) {
    suspend fun getUser(email:String) = apiServiceUser.getUser(email)
    suspend fun addUsers(user: PostUserResponse) = apiServiceUser.addUsers(user)
    suspend fun updateUser(user: PostUserResponse, id: String) = apiServiceUser.updateUser(user, id)
}