package mumtaz.telsa.palmapp.data.utils

import mumtaz.telsa.palmapp.network.ApiKebunServices

class KebunApiRepository constructor(private val kebunApiServices: ApiKebunServices) {

    fun getAllKebunApi() = kebunApiServices.getAllKebun()

}