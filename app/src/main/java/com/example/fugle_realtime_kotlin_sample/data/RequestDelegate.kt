package com.example.fugle_realtime_kotlin_sample.data

interface RequestDelegate {
    fun name(): String
    fun start(symbolId: String, token: String, callback: (String) -> Unit)
}