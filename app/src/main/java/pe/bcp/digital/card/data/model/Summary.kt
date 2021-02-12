package pe.bcp.digital.card.data.model

import com.google.gson.annotations.SerializedName

data class Summary(
    val availableAmount: Int,
    @SerializedName("virtualCardResponses") val digitalCards: List<Card>
)

data class Card(val cardNumber: String,
                val expirationDate: String,
                val ccv: String,
                val authorizedAmount: String,
                val availableAmount: String)
