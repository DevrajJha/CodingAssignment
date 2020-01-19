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
                .header("Cache-Control", "public, max-age=" + 60)
                .build()

        } else {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                .build()
        }

        return chain.proceed(request)
    }
}