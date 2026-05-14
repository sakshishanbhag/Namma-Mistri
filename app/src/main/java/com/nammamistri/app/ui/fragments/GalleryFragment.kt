package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nammamistri.app.databinding.FragmentGalleryBinding
import com.nammamistri.app.viewmodel.GalleryViewModel
import com.nammamistri.app.ui.adapters.PhotoAdapter
import com.nammamistri.app.R

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: GalleryViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PhotoAdapter()
        binding.photoGridView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }

        val siteId = arguments?.getLong("siteId") ?: -1L
        if (siteId != -1L) {
            viewModel.getPhotosBySite(siteId).observe(viewLifecycleOwner) { photos ->
                adapter.submitList(photos)
            }
        }

        binding.addPhotoButton.setOnClickListener {
            // Navigate to camera fragment
            val action = androidx.navigation.fragment.NavHostFragment.findNavController(this)
            val bundle = android.os.Bundle().apply {
                putLong("siteId", siteId)
            }
            action.navigate(R.id.cameraFragment, bundle)
        }
    }
}
