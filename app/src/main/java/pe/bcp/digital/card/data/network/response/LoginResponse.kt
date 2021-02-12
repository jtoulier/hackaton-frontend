package pe.bcp.digital.card.data.network.response

import com.google.gson.annotations.SerializedName
import pe.bcp.digital.card.data.model.User

data class LoginResponse(
    @SerializedName("dni") val dni : String,
    @SerializedName("firstSurname") val firstSurname : String,
    @SerializedName("secondSurname") val secondSurname : String,
    @SerializedName("givenName") val givenName : String,
){
    fun toUser(): User {
        return User(firstSurname, secondSurname, givenName, dni)
    }

}

