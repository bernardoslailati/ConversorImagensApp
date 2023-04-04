package com.example.conversorimagensapp.commom

import com.example.conversorimagensapp.commom.ConvertType.*
import java.util.UnknownFormatConversionException

// executar a conversão para os tipos desejados (JPG e PNG)

interface ImageBytesComplement

interface ImageConverter {
    fun convert(image: Image)
}

object BitmapToJPG: ImageConverter, ImageBytesComplement {
    
    override fun convert(image: Image) {
        // .....
        // 19208312903813
    }
    
}

object BitmapToPNG: ImageConverter {
    
    override fun convert(image: Image) {
        // .....
        // daiopsjdaijda
    }
    
}

object BitmapToSVG: ImageConverter {
    
    override fun convert(image: Image) {
        // .....
        // 1-02931-203lasd
    }
    
}

enum class ConvertType {
    UNKNOWN,
    JPG,
    PNG,
    SVG;
    
    companion object {
        fun fromString(convertType: String): ConvertType {
            return when (convertType) {
                "JPG" -> JPG
                "PNG" -> PNG
                "SVG" -> SVG
                else -> UNKNOWN
            }
        }
    }
}

object ImageConverterHelper {
    
    fun convert(image: Image, imageConverter: ConvertType) {
        when (imageConverter) {
            JPG -> BitmapToJPG
            PNG -> BitmapToPNG
            SVG -> BitmapToSVG
            else -> throw UnknownFormatConversionException("Formato desconhecido!")
        }
    }
    
}