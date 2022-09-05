package mumtaz.telsa.palmapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mumtaz.telsa.palmapp.data.utils.JasaApiRepository

class ViewModelFactoryJasaApi constructor(private val repository: JasaApiRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(JasaApiViewModel::class.java)){
            JasaApiViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("View model not found")
        }
    }

}