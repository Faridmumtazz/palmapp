package mumtaz.telsa.palmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentAddKebunBinding


class AddKebunFragment : Fragment() {

    private var _binding: FragmentAddKebunBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddKebunBinding.inflate(inflater, container, false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Add Kebun"


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}