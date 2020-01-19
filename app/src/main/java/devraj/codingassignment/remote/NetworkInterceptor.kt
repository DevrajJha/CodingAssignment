package devraj.codingassignment.remote

import android.content.Context
import com.ncornette.cache.OkCacheControl




class NetworkInterceptor(val context:Context): OkCacheControl.NetworkMonitor {
    override fun isOnline(): Boolean {
        return ConnectivityStatus.isConnected(context)
    }

}