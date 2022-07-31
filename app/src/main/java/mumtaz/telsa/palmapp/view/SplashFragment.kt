package mumtaz.telsa.palmapp.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import mumtaz.telsa.palmapp.R


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
                view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 2000)
        return view
    }


}