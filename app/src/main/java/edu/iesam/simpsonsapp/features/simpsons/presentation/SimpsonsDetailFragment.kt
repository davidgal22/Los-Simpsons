package edu.iesam.simpsonsapp.features.simpsons.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.simpsonsapp.databinding.FragmentSimpsonsDetailBinding
import edu.iesam.simpsonsapp.core.api.ApiClient
import edu.iesam.simpsonsapp.features.simpsons.data.SimpsonsDataSource
import edu.iesam.simpsonsapp.features.simpsons.data.remote.SimpsonsApiRemoteDataSource
import edu.iesam.simpsonsapp.features.simpsons.domain.GetSimpsonByIdUseCase
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons

class SimpsonsDetailFragment : Fragment() {

    private var _binding: FragmentSimpsonsDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SimpsonsDetailViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val remote = SimpsonsApiRemoteDataSource(ApiClient())
                val repo = SimpsonsDataSource(remote) // Changed from SimpsonsRepositoryImpl
                val useCase = GetSimpsonByIdUseCase(repo)
                return SimpsonsDetailViewModel(useCase) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpsonsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadSimpson(getSimpsonId())
    }

    private fun getSimpsonId(): String {
        return arguments?.getString("simpson_id") ?: ""
    }

    private fun setupObserver() {
        val observer = Observer<SimpsonsDetailViewModel.UiState> { uiState ->
            uiState.error?.let {
            }

            uiState.simpson?.let {
                showSimpsonDetail(it)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showSimpsonDetail(simpson: Simpsons) {
        binding.apply {
            tvDetailName.text = simpson.name
            tvDetailOccupation.text = simpson.occupation
            tvDetailAge.text = "Edad: ${simpson.age} años"
            ivDetailSimpson.load(simpson.urlImage) {
                crossfade(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}