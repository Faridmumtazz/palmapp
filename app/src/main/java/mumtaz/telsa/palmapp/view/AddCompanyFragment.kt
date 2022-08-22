package mumtaz.telsa.palmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentAddCompanyBinding
import mumtaz.telsa.palmapp.databinding.FragmentAddJasaBinding


class AddCompanyFragment : Fragment() {

    private var _binding: FragmentAddCompanyBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCompanyBinding.inflate(inflater, container, false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Add Company"


        return binding.root
    }


}