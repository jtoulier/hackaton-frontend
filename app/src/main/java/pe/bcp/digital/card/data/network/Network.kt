package pe.bcp.digital.card.data.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import okhttp3.logging.HttpLoggingInterceptor


object Network{

    val client = HttpClient(OkHttp) {
        defaultRequest {
            host = "70.37.86.87"
            url {
                protocol = URLProtocol.HTTP
                port = 8081
            }
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }

    }


}






