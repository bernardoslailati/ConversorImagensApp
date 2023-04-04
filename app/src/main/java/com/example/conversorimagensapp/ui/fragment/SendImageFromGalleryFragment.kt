package com.example.conversorimagensapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.conversorimagensapp.commom.*
import com.example.conversorimagensapp.commom.ConvertType.*
import com.example.conversorimagensapp.databinding.FragmentSendImageFromGalleryBinding
import com.example.conversorimagensapp.network.SendImageHelper
import com.example.conversorimagensapp.ui.utils.extension.gone
import com.example.conversorimagensapp.ui.utils.extension.toast
import com.example.conversorimagensapp.ui.utils.extension.visible
import java.util.UnknownFormatConversionException


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
    
    private var selectedImage: Image? = null
    private var selectedConvertType: ConvertType? = null
    
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { selectedImageUri ->
            if (selectedImageUri != null) {
                selectedImage = Image(uri = selectedImageUri)
                
                // mostrar a imagem selecionada no ImageView
                binding.ivPhotoFromGallery.setImageURI(selectedImageUri)
            } else {
                toast("Nenhuma imagem foi selecionada!")
            }
        }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }
    
    private fun setupView() {
        with(binding) {
            // ao clicar no iv, mostrar a janela de escolha de foto da galeria
            ivPhotoFromGallery.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            
            spnPhotoConvertType.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    if (position > 0 && selectedImage != null) {
                        btnSendConvertedPhoto.visible()
                        
                        val selectedConvertTypeString =
                            spnPhotoConvertType.adapter.getItem(position) as String
                        selectedConvertType = ConvertType.fromString(selectedConvertTypeString)
                        selectedImage?.let {
                            try {
                                ImageConverterHelper.convert(
                                    image = it,
                                    imageConverter = selectedConvertType ?: UNKNOWN
                                )
                            } catch (e: UnknownFormatConversionException) {
                                toast("Formato de conversão desconhecido! Escolha outra " +
                                        "opção e tente novamente.")
                            }
                        }
                    } else {
                        btnSendConvertedPhoto.gone()
                    }
                }
                
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            
            btnSendConvertedPhoto.setOnClickListener {
                // envio da imagem para o servidor (backend, Retrofit, ...)
                selectedImage?.let {
                    SendImageHelper.sendImage(image = it)
                    toast("Imagem convertida para $selectedConvertType enviada!")
                }
            }
        }
    }
}