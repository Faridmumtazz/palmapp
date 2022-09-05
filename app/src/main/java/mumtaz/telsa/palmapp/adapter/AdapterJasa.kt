package mumtaz.telsa.palmapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.ItemJasaBinding
import mumtaz.telsa.palmapp.model.GetAllJasaResponseItem

class AdapterJasa (private val onClick : (GetAllJasaResponseItem)-> Unit): RecyclerView.Adapter<AdapterJasa.ViewHolder>(){

    private var listJasa : List<GetAllJasaResponseItem>? = null

    fun setDataJasa(list: List<GetAllJasaResponseItem>){
        this.listJasa = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemJasaBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJasaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listJasa!![position]){
                binding.tvNamaJasa.text = nama
                binding.tvAlamat.text = alamat
                binding.tvDate.text = tanggalAngkut
                binding.tvKuantitasJasa.text = kuantitasTruk.toString()
                binding.tvBbm.text = jumlahBBM.toString()
                Glide.with(binding.imgJasa.context)
                    .load(image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imgJasa)
            }
        }
        holder.binding.itemJasa.setOnClickListener{
            onClick(listJasa!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (listJasa.isNullOrEmpty()){
            0
        }else{
            listJasa!!.size
        }
    }
}