package mumtaz.telsa.palmapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAllJasaResponseItem(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("biaya")
    val biaya: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("jumlahBBM")
    val jumlahBBM: Int,
    @SerializedName("kuantitasTruk")
    val kuantitasTruk: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("perusahaan")
    val perusahaan: String,
    @SerializedName("tanggalAngkut")
    val tanggalAngkut: String,
    @SerializedName("tanggalSampai")
    val tanggalSampai: String
) : Serializable