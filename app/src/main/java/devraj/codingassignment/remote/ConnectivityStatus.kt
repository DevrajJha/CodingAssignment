package devraj.codingassignment.remote

import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager

class ConnectivityStatus(base:Context) : ContextWrapper(base) {
companion object {
    fun isConnected(context: Context): Boolean {

        val manager =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connection = manager!!.getActiveNetworkInfo();
        if (connection != null && connection.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
}