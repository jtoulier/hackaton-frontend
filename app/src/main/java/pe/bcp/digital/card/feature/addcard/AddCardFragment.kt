package pe.bcp.digital.card.feature.addcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afollestad.materialdialogs.MaterialDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.bcp.digital.card.MainShareViewModel
import pe.bcp.digital.card.R
import pe.bcp.digital.card.databinding.AddCardFragmentBinding
import pe.bcp.digital.card.util.Dialog

class AddCardFragment : Fragment() {

    private var dialog: MaterialDialog? = null
    private val viewModel by viewModel<AddCardViewModel>()

    private val sharedVM: MainShareViewModel by activityViewModels()

    private var _binding : AddCardFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AddCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btCreate.setOnClickListener{
                viewModel.registerCard(amount = tilCredit.editText?.text.toString().toInt()
                    , expirationDate = tilExpiration.editText?.text.toString())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigateToHome.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { _ ->
                Dialog.showDialog(requireContext(), "Su tarjeta virtual ha sido creada satisfactoriamente." ){
                    sharedVM.notifyCard()
                }
            }
        }

        viewModel.progress.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { flag ->
                if(flag) showProgressDialog() else hideProgressDialog()
            }
        }

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