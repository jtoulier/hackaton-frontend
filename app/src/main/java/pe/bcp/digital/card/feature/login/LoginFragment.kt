package pe.bcp.digital.card.feature.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.bcp.digital.card.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.bcp.digital.card.R


class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private var _binding :LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
    }

}