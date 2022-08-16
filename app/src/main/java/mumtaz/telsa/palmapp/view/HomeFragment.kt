package mumtaz.telsa.palmapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.adapter.AdapterKebun
import mumtaz.telsa.palmapp.data.datastore.DataStoreManager
import mumtaz.telsa.palmapp.data.utils.KebunApiRepository
import mumtaz.telsa.palmapp.data.utils.Status
import mumtaz.telsa.palmapp.databinding.FragmentHomeBinding
import mumtaz.telsa.palmapp.network.ApiKebunServices
import mumtaz.telsa.palmapp.viewmodel.KebunApiViewModel
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel
import mumtaz.telsa.palmapp.viewmodel.ViewModelFactoryKebunApi
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModelUser: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)
    private lateinit var pref: DataStoreManager
    private val apiKebunServices = ApiKebunServices.getInstance()
    private lateinit var kebunViewModel: KebunApiViewModel
    private lateinit var adapterKebun: AdapterKebun

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""
        pref = DataStoreManager(requireContext())

        initRecyclerView()
        getKebunDataViewModel()

        viewModelUser.getEmail().observe(viewLifecycleOwner) {
            val email = it

            viewModelUser.getUser(email).observe(viewLifecycleOwner) { user ->
                when (user.status) {
                    Status.SUCCESS -> {
                        val data = user.data!![0]
                        (activity as AppCompatActivity?)!!.supportActionBar?.title =
                            "Welcome, ${
                                data.username.replaceFirstChar { userData ->
                                    if (userData.isLowerCase()) userData.titlecase(
                                        Locale.getDefault()
                                    ) else userData.toString()
                                }
                            }!"
                        viewModelUser.user.postValue(data)
                    }
                    Status.ERROR -> Toast.makeText(
                        requireContext(),
                        user.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Status.LOADING -> Log.d("loadingMsg", "Loading")
                }
            }
        }
        return binding.root
    }

    private fun initRecyclerView(){
        _binding!!.rvPerkebunan.layoutManager = LinearLayoutManager(requireContext())
        adapterKebun = AdapterKebun{
            val clickedKebun = bundleOf("KEBUNDATA" to it)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_detailKebunFragment, clickedKebun)
        }
        _binding!!.rvPerkebunan.adapter = adapterKebun
    }

    private fun getKebunDataViewModel(){
        kebunViewModel = ViewModelProvider(
            this, ViewModelFactoryKebunApi(KebunApiRepository(apiKebunServices))
        ).get(
            KebunApiViewModel::class.java
        )

        kebunViewModel.liveDataKebunApi.observe(viewLifecycleOwner){
            adapterKebun.setDataKebun(it)
        }
        kebunViewModel.getAllKebunApi()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                viewModelUser.user.observe(viewLifecycleOwner) {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_homeFragment_to_profileFragment)
                }
                true
            }
            else -> true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}