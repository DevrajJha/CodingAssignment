package devraj.codingassignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(@SerializedName("name") val name : String,
                @SerializedName("description") val description : String,
                @SerializedName("url") val url : String):Parcelable {
}