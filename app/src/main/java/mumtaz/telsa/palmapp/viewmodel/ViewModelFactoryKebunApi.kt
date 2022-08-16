package mumtaz.telsa.palmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mumtaz.telsa.palmapp.data.utils.KebunApiRepository

class ViewModelFactoryKebunApi constructor(private val repository: KebunApiRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(KebunApiViewModel::class.java)){
            KebunApiViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("View model not found")
        }
    }


}