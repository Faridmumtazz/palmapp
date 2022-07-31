package mumtaz.telsa.palmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mumtaz.telsa.palmapp.helper.SingleLiveEvent
import mumtaz.telsa.palmapp.model.GetAllUserResponseItem
import mumtaz.telsa.palmapp.model.PostUserResponse
import mumtaz.telsa.palmapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserApiViewModel : ViewModel() {

    val user = MutableLiveData<GetAllUserResponseItem>()

    val listUsers = SingleLiveEvent<List<GetAllUserResponseItem>>()
    val toastRegisterMessage = SingleLiveEvent<String>()
    val toastLoginMessage = SingleLiveEvent<String>()
    val loginStatus = SingleLiveEvent<Boolean>()
    val registerCheck = SingleLiveEvent<Boolean>()
    val loginCheck = SingleLiveEvent<Boolean>()

    fun getAllUsers(){
        ApiClient.instance.getAllUsers()
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body())
                        loginStatus.postValue(true)
                    } else{
                        loginStatus.postValue(false)
                        toastLoginMessage.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    loginStatus.postValue(false)
                    toastLoginMessage.postValue(t.message)
                }

            })
    }

    fun registerUser(user : PostUserResponse){
        ApiClient.instance.addUsers(user)
            .enqueue(object : Callback<GetAllUserResponseItem>{
                override fun onResponse(
                    call: Call<GetAllUserResponseItem>,
                    response: Response<GetAllUserResponseItem>
                ) {
                    if (!response.isSuccessful)
                        toastRegisterMessage.postValue(response.message())
                }

                override fun onFailure(call: Call<GetAllUserResponseItem>, t: Throwable) {
                    toastRegisterMessage.postValue(t.message)
                }

            })
    }
}