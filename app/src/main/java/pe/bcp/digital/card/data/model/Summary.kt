package pe.bcp.digital.card.data.model

data class Summary(
    val availableAmount: Int,
    val digitalCards: List<Card>
)

data class Card(val cardNumber: String,
                val expirationDate: String,
                val ccv: String,
                val authorizedAmount: String,
                val availableAmount: String)
