package com.darek.crosscountry.util

import com.darek.crosscountry.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestCoroutineDispatchersProvider(val coroutineDispatcher: CoroutineDispatcher)
    : CoroutineDispatchersProvider {
    override fun io(): CoroutineDispatcher = coroutineDispatcher
    override fun main(): CoroutineDispatcher = coroutineDispatcher
    override fun default(): CoroutineDispatcher = coroutineDispatcher
}