package mumtaz.telsa.palmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import mumtaz.telsa.palmapp.databinding.ActivityMainBinding
import mumtaz.telsa.palmapp.view.HomeFragment
import mumtaz.telsa.palmapp.view.ProfileFragment
import mumtaz.telsa.palmapp.view.WebviewFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener {_, destination, _ ->
            binding.apply {
                if (destination.id == R.id.homeFragment || destination.id == R.id.searchFragment || destination.id == R.id.addFragment || destination.id == R.id.chatFragment || destination.id == R.id.profileFragment){
                    navView.visibility = View.VISIBLE
                } else {
                    navView.visibility = View.GONE
                }
            }
        }

    }

}