package pe.bcp.digital.card.data.network.response

import com.google.gson.annotations.SerializedName
import pe.bcp.digital.card.data.model.User

data class LoginResponse(
    @SerializedName("code") val code : String,
    @SerializedName("token") val token : String,
    @SerializedName("user") val user : User
)