package com.example.conversorimagensapp.ui.utils.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), message, length).show()
}