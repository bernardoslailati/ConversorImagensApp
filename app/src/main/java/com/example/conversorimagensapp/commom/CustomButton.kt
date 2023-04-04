package com.example.conversorimagensapp.commom

interface OnClickButton {
    
    fun onClick()
    
}

interface OnLongClickButton {
    
    fun onLongClick()
    
}

interface OnDoubleClickButton {
    
    fun onDoubleClick()
    
}

class CustomButton: OnClickButton {
    
    override fun onClick() {
        // ...
    }
    
}