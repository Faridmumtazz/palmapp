package mumtaz.telsa.palmapp.view.dialogfragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentShowImageUserDialogBinding
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel

@AndroidEntryPoint
class ShowImageUserDialogFragment : DialogFragment() {

    private var _binding : FragmentShowImageUserDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModelUser: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowImageUserDialogBinding.inflate(inflater, container, false )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelUser.getImage().observe(viewLifecycleOwner) {
            if (it != "") {
                binding.imgUser.setImageBitmap(convertStringToBitmap(it))
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

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}