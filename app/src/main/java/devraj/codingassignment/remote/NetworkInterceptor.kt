package devraj.codingassignment.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import javax.xml.datatype.DatatypeConstants.DAYS
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit


class NetworkInterceptor(val context:Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (ConnectivityStatus.isConnected(context)) {
            request.newBuilder()
                .build()

        } else {
            request.newBuilder() .removeHeader("Pragma")
                .header(
                    "Cache-Control",
                    String.format("max-age=%d", 60))
                .build()

        }

        return chain.proceed(request)
    }
    private val REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        originalResponse.newBuilder()
            .removeHeader("Pragma")
            .header(
                "Cache-Control",
                String.format("max-age=%d", 60)
            )
            .build()
    }
}