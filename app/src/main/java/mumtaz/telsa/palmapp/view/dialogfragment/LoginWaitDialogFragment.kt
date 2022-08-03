package mumtaz.telsa.palmapp.view.dialogfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import mumtaz.telsa.palmapp.R
import mumtaz.telsa.palmapp.databinding.FragmentLoginWaitDialogBinding
import mumtaz.telsa.palmapp.viewmodel.UserApiViewModel


class LoginWaitDialogFragment : DialogFragment() {

    private var _binding : FragmentLoginWaitDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserApiViewModel by hiltNavGraphViewModels(R.id.navigation_component)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginWaitDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginStatus.postValue(false)

        viewModel.loginStatus.observe(viewLifecycleOwner){
            if (it){
                dialog?.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}