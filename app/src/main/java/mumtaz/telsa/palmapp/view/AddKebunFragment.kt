package mumtaz.telsa.palmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentAddKebunBinding
import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem
import mumtaz.telsa.palmapp.model.PostKebunResponse
import mumtaz.telsa.palmapp.network.PalmModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddKebunFragment : Fragment(){

    private var _binding: FragmentAddKebunBinding?= null
    private val binding get() = _binding!!

    var state = false

//    private lateinit var nama: String
//    private lateinit var alamat: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddKebunBinding.inflate(inflater, container, false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Add Kebun"


        setupViews()

        return binding.root
    }

//    override fun onClick(p0: View?) {
//        when (p0?.id) {
//            R.id.btn_submit_add -> {
//                setupViews()
//                p0.findNavController().navigate(R.id.action_addKebunFragment_to_homeFragment)
//
//            }
//        }
//    }

    private fun setupViews(){
        binding.apply {
            btnSubmitAdd.setOnClickListener {
                var nama = etNamaAdd.text.toString()
                var perusahaan = etPerusahaanAdd.text.toString()
                var biaya = etBiayaAdd.text.toString()
                var kuantitas = etKuantitasAdd.text.toString()
                var alamat = etAlamatAdd.text.toString()
                var tanggalPanen = etTanggalPanen.text.toString()
                var beratBuah = etBeratBuah.text.toString()
                var namaBibit = etNamaBibit.text.toString()
                var jenisBibit = etJenisBibit.text.toString()
                var hargaBibit = etJenisBibit.text.toString()
                var namaPupuk = etNamaPupuk.text.toString()
                var jenisPupuk = etJenisPupuk.text.toString()
                var hargaPupuk = etHargaPupuk.text.toString()
                var kapasitasPanen = etKapasitasPanen.text.toString()
                var kerapatanPanen = etKerapatanPanen.text.toString()
                var luasKebun = etLuasKebun.text.toString()
                var luasArealKebun = etLuasArealKebun.text.toString()
                var populasiTanaman = etPopulasiTanaman.text.toString()
                var hasilPanen = etHasilPanen.text.toString()
                var catatan = etCatatanAdd.text.toString()
                kebunAction(alamat,beratBuah,catatan,hargaBibit,hargaPupuk, hasilPanen, jenisBibit,jenisPupuk,kapasitasPanen,kerapatanPanen, kuantitas,luasKebun,luasArealKebun,nama,namaBibit,perusahaan,namaPupuk,populasiTanaman, tanggalPanen)
            }



        }
    }

    private fun kebunAction(
        alamat: String,
        beratBuah: String,
        catatan: String,
        hargaBibit: String,
        hargaPupuk: String,
        hasilPanen: String,
        jenisBibit: String,
        jenisPupuk: String,
        kapasitasPanen: String,
        kerapatanPanen: String,
        kuantitas: String,
        luasKebun: String,
        luasArealKebun: String,
        nama: String,
        namaBibit: String,
        perusahaan: String,
        namaPupuk: String,
        populasiTanaman: String,
        tanggalPanen: String

    ){
        val request = PostKebunResponse(
            alamat,
            beratBuah,
            catatan,
            "",
            hargaBibit,
            hargaPupuk,
            hasilPanen,
            jenisBibit,
            jenisPupuk,
            kapasitasPanen,
            kerapatanPanen,
            kuantitas,
            luasKebun,
            luasArealKebun,
            nama,
            namaBibit,
            perusahaan,
            namaPupuk,
            populasiTanaman,
            tanggalPanen,
        )

        PalmModule.instance.postKebun(request)
            .enqueue(object : Callback<GetAllKebunResponseItem>{
                override fun onResponse(
                    call: Call<GetAllKebunResponseItem>,
                    response: Response<GetAllKebunResponseItem>
                ) {
                    if (response.isSuccessful){
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_addKebunFragment_to_homeFragment)
                        Toast.makeText(requireContext(), "data berhasil dimasukkan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GetAllKebunResponseItem>, t: Throwable) {
                    //nothing
                }

            })
    }

    override fun onPause() {
        state = true
        super.onPause()
    }

    public override fun onDestroy() {
        if (state) super.onDestroy() else true
    }




}