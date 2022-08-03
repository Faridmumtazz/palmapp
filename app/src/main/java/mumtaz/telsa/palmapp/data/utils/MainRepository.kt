package mumtaz.telsa.palmapp.data.utils

import androidx.lifecycle.LiveData
import mumtaz.telsa.palmapp.model.PostUserResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {




    suspend fun getUser(email: String) = apiHelper.getUser(email)
    suspend fun addUsers(user: PostUserResponse) = apiHelper.addUsers(user)
    suspend fun updateUser(user: PostUserResponse, id: String) = apiHelper.updateUser(user, id)


}