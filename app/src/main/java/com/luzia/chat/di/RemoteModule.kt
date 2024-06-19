package com.luzia.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URI

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {


    @Provides
    fun provideSocket(): Socket =
        IO.socket(URI.create("http://10.0.2.2"), IO.Options().apply {
            port = 3002
        })
}
