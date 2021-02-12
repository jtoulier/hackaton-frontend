package pe.bcp.digital.card.data.network.request

data class RegisterCardRequest(val dni: String, val amount: Int, val expirationDate: String)
