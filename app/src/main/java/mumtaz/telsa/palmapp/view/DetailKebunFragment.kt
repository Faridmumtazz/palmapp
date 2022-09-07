package mumtaz.telsa.palmapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.bumptech.glide.Glide
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.adapter.AdapterKebun
import mumtaz.telsa.palmapp.data.datastore.DataStoreManager
import mumtaz.telsa.palmapp.databinding.FragmentDetailKebunBinding
import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem
import mumtaz.telsa.palmapp.network.ApiKebunServices
import mumtaz.telsa.palmapp.viewmodel.KebunApiViewModel
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel


class DetailKebunFragment : Fragment(R.layout.fragment_detail_kebun) {

    private var fragmentDetailBinding : FragmentDetailKebunBinding? = null

    private val viewModelUser: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)
    private lateinit var pref: DataStoreManager
    private val apiKebunServices = ApiKebunServices.getInstance()
    private lateinit var kebunViewModel: KebunApiViewModel
    private lateinit var adapterKebun: AdapterKebun

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailKebunBinding.bind(view)
        fragmentDetailBinding = binding

        getAllDetails()

    }

    @SuppressLint("SetTextI18n")
    private fun getAllDetails(){
        if (requireArguments().containsKey("KEBUNDATA")){
            val kebunDetail = arguments?.getSerializable("KEBUNDATA") as GetAllKebunResponseItem
            fragmentDetailBinding!!.tvDetailNamaKebun.text = kebunDetail.namaKebun
            fragmentDetailBinding!!.tvDetailTanggalPanenIsi.text = kebunDetail.tanggalPanen
            fragmentDetailBinding!!.tvDetailNamaOrangIsi.text = kebunDetail.nama
            fragmentDetailBinding!!.tvDetailKuantitasIsi.text = kebunDetail.kuantitas.toString()
            fragmentDetailBinding!!.tvDetailKapasitasPanenIsi.text = kebunDetail.kapasitasPanen.toString()
            fragmentDetailBinding!!.tvDetailLuasKebunIsi.text = kebunDetail.luas.toString()
            fragmentDetailBinding!!.tvDetailLuasArealKebunIsi.text = kebunDetail.luasPanen.toString()
            fragmentDetailBinding!!.tvDetailTokoBibitIsi.text = kebunDetail.namaBibit
            fragmentDetailBinding!!.tvDetailJenisBibitIsi.text = kebunDetail.jenisBibit
            fragmentDetailBinding!!.tvDetailHargaBibitIsi.text = kebunDetail.hargaBibit.toString()
            fragmentDetailBinding!!.tvDetailTokoPupukIsi.text = kebunDetail.namaPupuk
            fragmentDetailBinding!!.tvDetailJenisPupukIsi.text = kebunDetail.jenisPupuk
            fragmentDetailBinding!!.tvDetailHargaPupukIsi.text = kebunDetail.hargaPupuk.toString()
            fragmentDetailBinding!!.tvDetailPopulasiTanamanIsi.text = kebunDetail.hargaPupuk.toString()
            fragmentDetailBinding!!.tvDetailAlamattIsi.text = kebunDetail.alamat
            fragmentDetailBinding!!.tvDetailCatatanIsi.text = kebunDetail.description
            Glide.with(fragmentDetailBinding!!.imgDetailPerkebunan.context)
                .load(kebunDetail.gambarProduk)
                .error(R.drawable.ic_launcher_background)
                .override(780, 450)
                .into(fragmentDetailBinding!!.imgDetailPerkebunan)
        }
    }



}