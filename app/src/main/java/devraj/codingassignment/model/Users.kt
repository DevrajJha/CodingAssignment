package devraj.codingassignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(@SerializedName("username") val username : String,
                 @SerializedName("name") val name : String,
                 @SerializedName("type") val type : String,
                 @SerializedName("url") val url : String,
                 @SerializedName("avatar") val avatar : String,
                 @SerializedName("repo") val repo : Repo):Parcelable {
}