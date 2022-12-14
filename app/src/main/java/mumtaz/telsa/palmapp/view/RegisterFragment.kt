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
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.Navigation
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.data.utils.Status
import mumtaz.telsa.palmapp.databinding.FragmentRegisterBinding
import mumtaz.telsa.palmapp.model.GetAllUserResponseItem
import mumtaz.telsa.palmapp.model.PostUserResponse
import mumtaz.telsa.palmapp.view.dialogfragment.RegisterWaitDialogFragment
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel
import java.util.regex.Pattern


class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String
    private var cek: Boolean = false
    private var viewPass: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Register"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegister.setOnClickListener(this@RegisterFragment)
            btnViewPass.setOnClickListener(this@RegisterFragment)
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_register -> {
                register()
            }
            R.id.btn_view_pass -> {
                if (!viewPass) {
                    binding.apply {
                        btnViewPass.setImageResource(R.drawable.ic_green_eye_24)
                        etPasswordRegis.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }
                    viewPass = true
                } else {
                    binding.apply {
                        btnViewPass.setImageResource(R.drawable.ic_outline_remove_green_eye_24)
                        etPasswordRegis.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                    }
                    viewPass = false
                }
            }
        }
    }

    private fun register(){
        binding.apply {
            name = etNamaLengkapRegis.text.toString()
            email = etEmailRegis.text.toString()
            password = etPasswordRegis.text.toString()
            cek = isValidEmail(email)
        }

        if (inputCheck(name, email, password, cek)) {
            registerUser(name, email, password)
        }
    }

    private fun registerUser(username : String, email : String, password: String){
        val regUser = PostUserResponse(
            "",
            "",
            email,
            "",
            password,
            username
        )

        if (inputCheck(username, email, password, cek)) {

            viewModel.getUser(email).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        val data = it.data!!
                        if (data.isNotEmpty()) {
                            viewModel.registerCheck.postValue(true)
                            Toast.makeText(
                                requireContext(),
                                "Email has already used by another user",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.registerUser(regUser).observe(viewLifecycleOwner) { reg ->
                                when (reg.status) {
                                    Status.SUCCESS -> {
                                        viewModel.registerCheck.postValue(true)
                                        Toast.makeText(
                                            requireContext(),
                                            "Register successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Navigation.findNavController(requireView())
                                            .navigate(R.id.action_registerFragment_to_loginFragment)
                                    }
                                    Status.ERROR -> {
                                        viewModel.registerCheck.postValue(true)
                                        Log.d("errMsg", reg.message.toString())
                                    }
                                    Status.LOADING -> Log.d("loadingMsg", reg.message.toString())
                                }
                            }
                        }
                    }
                    Status.ERROR -> Log.d("errMsg", it.message.toString())
                    Status.LOADING -> RegisterWaitDialogFragment().show(requireActivity().supportFragmentManager, null)
                }

            }
        }
    }

    private fun inputCheck(name: String, email: String, password: String, cek: Boolean): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || !cek
            || password.length < 6
        ) {
            if (name.isEmpty()) {
                binding.apply {
                    etNamaLengkapRegis.error = "Username can't be empty"
                    etNamaLengkapRegis.requestFocus()
                }

            }
            if (email.isEmpty()) {
                binding.apply {
                    etEmailRegis.error = "Email can't be empty"
                    etEmailRegis.requestFocus()
                }
            }
            if (password.isEmpty()) {
                binding.apply {
                    etPasswordRegis.error = "Password can't be empty"
                    etPasswordRegis.requestFocus()
                }
            }
            if (!cek) {
                binding.apply {
                    etEmailRegis.error = "Incorrect email format"
                    etEmailRegis.requestFocus()
                }
            }

            if (password.length < 6) {
                binding.apply {
                    etPasswordRegis.error = "Minimum 6 characters"
                    etPasswordRegis.requestFocus()
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