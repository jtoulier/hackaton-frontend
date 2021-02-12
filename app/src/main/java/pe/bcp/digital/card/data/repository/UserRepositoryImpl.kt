package pe.bcp.digital.card.data.repository

import android.util.Log
import pe.bcp.digital.card.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import pe.bcp.digital.card.data.model.User
import pe.bcp.digital.card.data.model.UserSession
import pe.bcp.digital.card.data.network.HttpConstants
import pe.bcp.digital.card.data.network.request.LoginRequest
import java.lang.Exception
import pe.bcp.digital.card.data.network.response.LoginResponse

class UserRepositoryImpl(private val client: HttpClient, private val userSession: UserSession): UserRepository {

    override suspend fun login(document: String, pwd: String): Result<User> {
         return try {
            val response = client.post<LoginResponse>(path = HttpConstants.LOGIN) {
                contentType(ContentType.Application.Json)
                body = LoginRequest(document, pwd)
            }
             val user = response.toUser()
             userSession.user = user
            Result.Success(user)
        }catch (t: Throwable){
             Result.Error(Exception("Error"))
        }
    }

}