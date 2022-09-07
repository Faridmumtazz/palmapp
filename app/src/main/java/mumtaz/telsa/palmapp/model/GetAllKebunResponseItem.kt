package mumtaz.telsa.palmapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAllKebunResponseItem(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("beratBuah")
    val beratBuah: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("gambarProduk")
    val gambarProduk: String,
    @SerializedName("hargaBibit")
    val hargaBibit: Int,
    @SerializedName("hargaPupuk")
    val hargaPupuk: Int,
    @SerializedName("hasilPanen")
    val hasilPanen: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("jenisBibit")
    val jenisBibit: String,
    @SerializedName("jenisPupuk")
    val jenisPupuk: String,
    @SerializedName("kapasitasPanen")
    val kapasitasPanen: Int,
    @SerializedName("kerapatanPanen")
    val kerapatanPanen: Int,
    @SerializedName("kuantitas")
    val kuantitas: Int,
    @SerializedName("luas")
    val luas: Int,
    @SerializedName("luasPanen")
    val luasPanen: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaBibit")
    val namaBibit: String,
    @SerializedName("namaKebun")
    val namaKebun: String,
    @SerializedName("namaPupuk")
    val namaPupuk: String,
    @SerializedName("populasiTanaman")
    val populasiTanaman: Int,
    @SerializedName("tanggalPanen")
    val tanggalPanen: String
) : Serializable