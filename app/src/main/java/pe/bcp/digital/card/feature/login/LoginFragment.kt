package pe.bcp.digital.card.feature.login

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.bcp.digital.card.R
import pe.bcp.digital.card.databinding.LoginFragmentBinding
import pe.bcp.digital.card.util.Dialog


class LoginFragment : Fragment() {

    private var dialog: MaterialDialog? = null
    private val viewModel by viewModel<LoginViewModel>()
    private var _binding :LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnLogin.setOnClickListener{
                viewModel.login(etDocument.text.toString(), etPwd.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigateToHome.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { _ ->
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        viewModel.progress.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { flag ->
                if(flag) showProgressDialog() else hideProgressDialog()
            }
        }

        viewModel.message.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { message ->
                showMessage(message)
            }
        }
    }

    fun showMessage(message: String){
        Dialog.showDialog(requireContext(), message)
    }

    fun showProgressDialog(){
        dialog = MaterialDialog.Builder(requireContext())
            .content(R.string.login_loading)
            .progress(true, 0)
            .cancelable(false)
            .show()
    }

    fun hideProgressDialog(){
        dialog?.hide()
    }

}