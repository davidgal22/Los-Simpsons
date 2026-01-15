package edu.iesam.simpsonsapp.features.simpsons.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpsonsapp.R
import com.example.simpsonsapp.databinding.FragmentSimpsonsListBinding
import edu.iesam.simpsonsapp.core.api.ApiClient
import edu.iesam.simpsonsapp.features.simpsons.data.SimpsonsDataSource
import edu.iesam.simpsonsapp.features.simpsons.data.remote.SimpsonsApiRemoteDataSource
import edu.iesam.simpsonsapp.features.simpsons.domain.GetSimpsonsUseCase

class SimpsonsListFragment : Fragment() {

    private var _binding: FragmentSimpsonsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SimpsonsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val remote = SimpsonsApiRemoteDataSource(ApiClient())
                val repo = SimpsonsDataSource(remote) // Changed from SimpsonsRepositoryImpl
                val useCase = GetSimpsonsUseCase(repo)
                return SimpsonsViewModel(useCase) as T
            }
        }
    }

    private lateinit var adapter: SimpsonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpsonsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SimpsonsAdapter { simpson ->
            val bundle = bundleOf("simpson_id" to simpson.id)
            findNavController().navigate(R.id.action_list_to_detail, bundle)
        }

        binding.rvSimpsons.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSimpsons.adapter = adapter

        viewModel.simpsonsList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
        }

        viewModel.loadSimpsons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}