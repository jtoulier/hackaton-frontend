package pe.bcp.digital.card.data.repository

import pe.bcp.digital.card.data.model.Summary
import pe.bcp.digital.card.util.Result

interface CardRepository {
    suspend fun getSummary(): Result<Summary>

    suspend fun addCard(amount: Int, expirationDate: String): Result<Unit>
}