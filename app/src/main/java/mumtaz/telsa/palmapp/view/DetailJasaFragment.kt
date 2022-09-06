package mumtaz.telsa.palmapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentDetailJasaBinding
import mumtaz.telsa.palmapp.databinding.FragmentDetailKebunBinding
import mumtaz.telsa.palmapp.model.GetAllJasaResponseItem


class DetailJasaFragment : Fragment(R.layout.fragment_detail_jasa) {

    private var fragmentDetailBinding: FragmentDetailJasaBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding =  FragmentDetailJasaBinding.bind(view)
        fragmentDetailBinding = binding

        getAllDetails()
    }

    @SuppressLint("SetTextI18n")
    private fun getAllDetails(){
        if (requireArguments().containsKey("JASADATA")){
            val jasaDetail = arguments?.getSerializable("JASADATA") as GetAllJasaResponseItem
            fragmentDetailBinding!!.tvDetailNamaJasa.text = jasaDetail.perusahaan
            fragmentDetailBinding!!.tvDetailNamaOrangIsi.text = jasaDetail.nama
            fragmentDetailBinding!!.tvDetailTanggalAngkutIsi.text = jasaDetail.tanggalAngkut
            fragmentDetailBinding!!.tvDetailTanggalSampaiIsi.text = jasaDetail.tanggalSampai
            fragmentDetailBinding!!.tvDetailKuantitasTrukIsi.text = jasaDetail.kuantitasTruk.toString()
            fragmentDetailBinding!!.tvDetailJumlahBbmIsi.text = jasaDetail.jumlahBBM.toString()
            fragmentDetailBinding!!.tvDetailBiayaIsi.text = jasaDetail.biaya.toString()
            fragmentDetailBinding!!.tvDetailAlamatIsi.text = jasaDetail.alamat
            Glide.with(fragmentDetailBinding!!.imgDetailJasa.context)
                .load(jasaDetail.image)
                .error(R.drawable.ic_launcher_background)
                .override(780,450)
                .into(fragmentDetailBinding!!.imgDetailJasa)

        }
    }


}