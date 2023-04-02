package com.example.conversorimagensapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.conversorimagensapp.databinding.FragmentSendImageFromGalleryBinding


class SendImageFromGalleryFragment : Fragment() {
    
    private var _binding: FragmentSendImageFromGalleryBinding? = null
    private val binding get() = _binding!!
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSendImageFromGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }
    
    private fun setupView() {
        TODO("Not yet implemented")
    }
}