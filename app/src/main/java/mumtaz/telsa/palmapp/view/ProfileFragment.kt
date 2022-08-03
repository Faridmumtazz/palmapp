package mumtaz.telsa.palmapp.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentProfileBinding
import mumtaz.telsa.palmapp.view.dialogfragment.ShowImageUserDialogFragment
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModelUser: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Profile"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        viewModelUser.user.observe(viewLifecycleOwner) { user ->
            binding.apply {
                if (user.fullName != "") {
                    tvName.text = user.fullName
                }
                val txtUname = "@${user.username}"
                tvUsername.text = txtUname
            }
        }
        viewModelUser.getImage().observe(viewLifecycleOwner) {
            if (it != "") {
                binding.imgUser.setImageBitmap(convertStringToBitmap(it))
                binding.imgUser.setOnClickListener {
                    ShowImageUserDialogFragment().show(
                        requireActivity().supportFragmentManager,
                        null
                    )
                }
            }
        }


    }

    private fun convertStringToBitmap(string: String?): Bitmap? {
        val byteArray1: ByteArray = Base64.decode(string, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(
            byteArray1, 0,
            byteArray1.size
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                AlertDialog.Builder(requireContext()).setTitle("Logout")
                    .setMessage("Are you sure?")
                    .setIcon(R.drawable.palmapp)
                    .setPositiveButton("Yes") { _, _ ->
                        viewModelUser.setImage("")
                        viewModelUser.setEmail("")
                        val navOptions =
                            NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
                        Navigation.findNavController(requireView()).navigate(
                            R.id.action_profileFragment_to_loginFragment,
                            null,
                            navOptions
                        )
                    }.setNegativeButton("No") { _, _ ->

                    }
                    .show()

                true
            }
            R.id.edit_profile -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_profileFragment_to_editProfileFragment)
                true
            }
            else -> true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}