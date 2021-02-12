package pe.bcp.digital.card.data.repository

import pe.bcp.digital.card.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import pe.bcp.digital.card.data.model.User
import pe.bcp.digital.card.data.network.HttpConstants
import java.lang.Exception
import pe.bcp.digital.card.data.network.response.LoginResponse

class UserRepositoryImpl(private val client: HttpClient): UserRepository {

    override suspend fun login(user: String, pwd: String): Result<User> {
         return try {
            val response = client.post<LoginResponse>(path = HttpConstants.LOGIN) {
                body = MultiPartFormDataContent(formData {
                    append("user", user)
                    append("pass", pwd)
                })
            }
            Result.Success(response.user)
        }catch (t: Throwable){
            Result.Error(Exception("Error"))
        }
    }

}