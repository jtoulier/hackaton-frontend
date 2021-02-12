package pe.bcp.digital.card.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.bcp.digital.card.MainShareViewModel
import pe.bcp.digital.card.R
import pe.bcp.digital.card.databinding.HomeFragmentBinding
import pe.bcp.digital.card.feature.home.adapter.CardAdapter

class HomeFragment : Fragment() {

    private val sharedVM: MainShareViewModel by activityViewModels()
    private val viewModel by viewModel<HomeViewModel>()
    private var _binding : HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rcvDigitalCard){
            layoutManager = LinearLayoutManager(context)
            adapter = CardAdapter()
        }

        with(binding){
            btnAdd.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_addCardFragment)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.summary.observe(viewLifecycleOwner){
            binding.tvCredit.text = "Tu linea de saldo es de : S/." + it.availableAmount.toString()
            (binding.rcvDigitalCard.adapter as CardAdapter).items = it.digitalCards.toMutableList()
        }

        viewModel.progress.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        }

        sharedVM.notifyCard.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { _ ->
                viewModel.getSummary()
            }
        }


    }

}