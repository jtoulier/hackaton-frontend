package pe.bcp.digital.card.data.repository

import pe.bcp.digital.card.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import pe.bcp.digital.card.data.model.User
import pe.bcp.digital.card.data.network.HttpConstants
import pe.bcp.digital.card.data.network.request.LoginRequest
import java.lang.Exception
import pe.bcp.digital.card.data.network.response.LoginResponse

class UserRepositoryImpl(private val client: HttpClient): UserRepository {

    override suspend fun login(document: String, pwd: String): Result<User> {
         return try {
            val response = client.post<LoginResponse>(path = HttpConstants.LOGIN) {
                body = LoginRequest(document, pwd)
            }
            Result.Success(response.toUser())
        }catch (t: Throwable){
            Result.Error(Exception("Error"))
        }
    }

}