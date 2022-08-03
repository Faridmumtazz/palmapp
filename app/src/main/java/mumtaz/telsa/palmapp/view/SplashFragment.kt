package mumtaz.telsa.palmapp.view


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentSplashBinding
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel


class SplashFragment : Fragment() {


    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel : UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()



        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getEmail().observe(viewLifecycleOwner) {

                if (it != ""){
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }, 3000)
    }


}