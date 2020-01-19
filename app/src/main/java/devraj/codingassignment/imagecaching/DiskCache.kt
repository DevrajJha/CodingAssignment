package devraj.codingassignment.imagecaching

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.jakewharton.disklrucache.DiskLruCache
import java.io.*
import android.provider.SyncStateContract.Helpers.update
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class DiskCache(context: Context) : ImageCache {
    private var cache: DiskLruCache = DiskLruCache.open(context.cacheDir, 1, 1, 10 * 1024 * 1024)

    override fun get(url: String): Bitmap? {
        val key = md5(url)
        val snapshot: DiskLruCache.Snapshot? = cache.get(key)
        return if (snapshot != null) {
            val inputStream: InputStream = snapshot.getInputStream(0)
            val buffIn = BufferedInputStream(inputStream, 8 * 1024)
            BitmapFactory.decodeStream(buffIn)
        } else {
            null
        }
    }

    override fun put(url: String, bitmap: Bitmap) {
        val key: String = md5(url)
        var editor: DiskLruCache.Editor? = null
        try {
            editor = cache.edit(key)
            if (editor == null) {
                return
            }
            if (writeBitmapToFile(bitmap, editor)) {
                cache.flush()
                editor.commit()
            } else {
                editor.abort()
            }
        } catch (e: IOException) {
            try {
                editor?.abort()
            } catch (ignored: IOException) {
            }
        }
    }

    override fun clear() {
        cache.delete()
    }

    private fun writeBitmapToFile(bitmap: Bitmap, editor: DiskLruCache.Editor): Boolean {
        var out: OutputStream? = null
        try {
            out = BufferedOutputStream(editor.newOutputStream(0), 8 * 1024)
            return bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        } finally {
            out?.close()
        }
    }
    private fun md5(s:String):String{
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(s.toByteArray())).toString(16).padStart(32, '0')
    }
}