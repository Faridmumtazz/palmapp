package mumtaz.telsa.palmapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class GetAllKebunResponseItem(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("gambarProduk")
    val gambarProduk: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kuantitas")
    val kuantitas: Int,
    @SerializedName("namaKebun")
    val namaKebun: String,
    @SerializedName("tanggalPanen")
    val tanggalPanen: String
) : Serializable