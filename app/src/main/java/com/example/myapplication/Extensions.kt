package com.example.myapplication

fun <T> List<T>.toExtensionString() : String {
    return "Extension: $this"
}