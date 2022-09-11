package mumtaz.telsa.palmapp.model

import com.google.gson.annotations.SerializedName

data class PostKebunResponse(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("beratBuah")
    val beratBuah: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("gambarProduk")
    val gambarProduk: String,
    @SerializedName("hargaBibit")
    val hargaBibit: String,
    @SerializedName("hargaPupuk")
    val hargaPupuk: String,
    @SerializedName("hasilPanen")
    val hasilPanen: String,
    @SerializedName("jenisBibit")
    val jenisBibit: String,
    @SerializedName("jenisPupuk")
    val jenisPupuk: String,
    @SerializedName("kapasitasPanen")
    val kapasitasPanen: String,
    @SerializedName("kerapatanPanen")
    val kerapatanPanen: String,
    @SerializedName("kuantitas")
    val kuantitas: String,
    @SerializedName("luas")
    val luas: String,
    @SerializedName("luasPanen")
    val luasPanen:String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaBibit")
    val namaBibit: String,
    @SerializedName("namaKebun")
    val namaKebun: String,
    @SerializedName("namaPupuk")
    val namaPupuk: String,
    @SerializedName("populasiTanaman")
    val populasiTanaman: String,
    @SerializedName("tanggalPanen")
    val tanggalPanen: String
)
