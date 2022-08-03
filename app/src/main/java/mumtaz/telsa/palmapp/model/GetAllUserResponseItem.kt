package mumtaz.telsa.palmapp.model


import com.google.gson.annotations.SerializedName

data class GetAllUserResponseItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)