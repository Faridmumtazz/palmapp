package mumtaz.telsa.palmapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mumtaz.telsa.palmapp.data.datastore.DataStoreManager
import mumtaz.telsa.palmapp.data.utils.MainRepository
import mumtaz.telsa.palmapp.data.utils.Resource
import mumtaz.telsa.palmapp.helper.SingleLiveEvent
import mumtaz.telsa.palmapp.model.GetAllUserResponseItem
import mumtaz.telsa.palmapp.model.PostUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserApiViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val pref: DataStoreManager
): ViewModel() {

    val user = MutableLiveData<GetAllUserResponseItem>()

    val listUsers = SingleLiveEvent<List<GetAllUserResponseItem>>()
//    val toastRegisterMessage = SingleLiveEvent<String>()
//    val toastLoginMessage = SingleLiveEvent<String>()
    val loginStatus = SingleLiveEvent<Boolean>()
    val registerCheck = SingleLiveEvent<Boolean>()
//    val loginCheck = SingleLiveEvent<Boolean>()



    fun setEmail(email: String) {
        viewModelScope.launch {
            pref.setUser(email)
        }
    }

    fun getEmail(): LiveData<String> {
        return pref.getUser().asLiveData()
    }

    fun setImage(img: String) {
        viewModelScope.launch {
            pref.setImageCamera(img)
        }
    }
    fun getImage(): LiveData<String> {
        return pref.getImage().asLiveData()
    }
    fun getUser(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.getUser(email)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }
    }
    fun registerUser(user: PostUserResponse) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.addUsers(user)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }
    }
    fun updateUser(user: PostUserResponse, id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.updateUser(user, id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }
    }
}