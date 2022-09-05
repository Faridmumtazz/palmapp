package mumtaz.telsa.palmapp.data.utils

import mumtaz.telsa.palmapp.network.ApiJasaServices
import mumtaz.telsa.palmapp.network.ApiKebunServices

class JasaApiRepository constructor(private val jasaApiServices: ApiJasaServices) {

    fun getAllJasaApi() = jasaApiServices.getAllJasa()

}