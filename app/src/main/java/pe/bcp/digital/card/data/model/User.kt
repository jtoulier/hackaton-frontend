package pe.bcp.digital.card.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("first_name") val firstName : String,
    @SerializedName("last_name") val lastName : String,
    @SerializedName("doc_type") val docType : String,
    @SerializedName("doc_nro") val docNumber : Int,
    @SerializedName("email") val email : String
)