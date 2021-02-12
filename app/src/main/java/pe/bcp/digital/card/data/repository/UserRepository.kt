package pe.bcp.digital.card.data.repository

import pe.bcp.digital.card.data.model.User
import pe.bcp.digital.card.util.Result

interface UserRepository {

    suspend fun login(document: String, pwd: String): Result<User>

}
