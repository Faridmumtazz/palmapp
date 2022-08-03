package mumtaz.telsa.palmapp.view


import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.data.utils.Status
import mumtaz.telsa.palmapp.databinding.FragmentLoginBinding
import mumtaz.telsa.palmapp.view.dialogfragment.LoginWaitDialogFragment
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel
import java.util.regex.Pattern


class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)

    private lateinit var email : String
    private lateinit var password: String
    private var viewPass: Boolean = false
    private var cek: Boolean = false
    private var cekEmail : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Login"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewModel.getEmail().observe(viewLifecycleOwner) {
            cekEmail = it
            Log.d("cekEmail", cekEmail)
        }
        binding.btnLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)
        binding.btnViewPass.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_login -> {
                viewModel.loginStatus.value = false
                login()
            }
            R.id.btn_signup -> {
                p0.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            R.id.btn_view_pass -> {
                if (!viewPass) {
                    binding.apply {
                        btnViewPass.setImageResource(R.drawable.ic_green_eye_24)
                        etPassword.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }
                    viewPass = true
                } else {
                    binding.apply {
                        btnViewPass.setImageResource(R.drawable.ic_outline_remove_green_eye_24)
                        etPassword.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                    }
                    viewPass = false
                }
            }
        }
    }

    private fun login(){
        binding.apply {
            email = etEmail.text.toString()
            password = etPassword.text.toString()
            cek = isValidEmail(email)
        }

        if (inputCheck(email, password, cek)) {

            viewModel.getUser(email).observe(viewLifecycleOwner){
                when (it.status) {
                    Status.SUCCESS -> {
                        val data = it.data!!
                        viewModel.loginStatus.postValue(true)
                        if (data.isNotEmpty()){
                            if (password == data[0].password){
                                viewModel.setEmail(email)
                                Navigation.findNavController(requireView())
                                    .navigate(R.id.action_loginFragment_to_homeFragment)
                            }else{
                                Toast.makeText(requireContext(), "Wrong password", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(requireContext(), "Email not registered", Toast.LENGTH_SHORT).show()
                        }

                    }
                    Status.ERROR -> {
                        viewModel.loginStatus.postValue(true)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        LoginWaitDialogFragment().show(
                            requireActivity().supportFragmentManager,
                            null
                        )
                    }
                }
            }
        }
    }

    private fun inputCheck(email: String, password: String, cek: Boolean): Boolean {
        if (email.isEmpty() || password.isEmpty() || !cek
        ) {
            if (email.isEmpty()) {
                binding.apply {
                    etEmail.error = "Email can't be empty"
                    etEmail.requestFocus()
                }

            }
            if (password.isEmpty()) {
                binding.apply {
                    etPassword.error = "Password can't be empty"
                    etPassword.requestFocus()
                }
            }
            if (!cek) {
                binding.apply {
                    etEmail.error = "Email can't be empty"
                    etEmail.requestFocus()
                }
            }
            return false
        } else {
            return true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9+._%\\-]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailPattern.matcher(email).matches()
    }


}