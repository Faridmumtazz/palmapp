package mumtaz.telsa.palmapp.model

import com.google.gson.annotations.SerializedName

data class PostUserResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)
