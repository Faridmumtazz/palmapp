package mumtaz.telsa.palmapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.ItemPerkebunanBinding
import mumtaz.telsa.palmapp.model.GetAllKebunResponseItem


class AdapterKebun (private val onClick : (GetAllKebunResponseItem) -> Unit) : RecyclerView.Adapter<AdapterKebun.ViewHolder>(){

    private var listKebun: List<GetAllKebunResponseItem>? = null

    fun setDataKebun(list: List<GetAllKebunResponseItem>){
        this.listKebun = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemPerkebunanBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPerkebunanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listKebun!![position]){
                binding.tvNamaKebun.text = nama
                binding.tvAlamat.text = alamat
                binding.tvDate.text = tanggalPanen
                binding.tvTon.text = kuantitas.toString()
                binding.tvKuantitas.text = kuantitas.toString()
                Glide.with(binding.imgPerkebunan.context)
                    .load(gambarProduk)
                    .error(R.drawable.kebunn)
                    .into(binding.imgPerkebunan)
            }
        }
        holder.binding.itemKebun.setOnClickListener{
            onClick(listKebun!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (listKebun.isNullOrEmpty()){
            0
        }else{
            listKebun!!.size
        }
    }
}


