package pe.bcp.digital.card.data.repository

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import pe.bcp.digital.card.data.model.Summary
import pe.bcp.digital.card.data.model.UserSession
import pe.bcp.digital.card.data.network.HttpConstants
import pe.bcp.digital.card.data.network.request.RegisterCardRequest
import pe.bcp.digital.card.util.Result
import java.lang.Exception

class CardRepositoryImpl(private val httpClient: HttpClient, private val session: UserSession): CardRepository {

    override suspend fun getSummary(): Result<Summary>{
        return try {
            val response = httpClient.get<Summary>(path = "S${HttpConstants.SUMMARY}${session.user!!.document}"){
            }
            Result.Success(response)
        }catch (t: Throwable){
            Result.Error(Exception("Error"))
        }
    }


    override suspend fun addCard(amount: Int, expirationDate: String): Result<Unit> {
        return try {
            val response = httpClient.get<HttpResponse>(path = HttpConstants.REGISTER_CARD){
                body = RegisterCardRequest(session.user!!.document, amount, expirationDate)
            }

            if(response.status.value == 201) Result.Success(Unit) else Result.Error(Exception("Error"))

        }catch (t: Throwable){
            Result.Error(Exception("Error"))
        }
    }


}