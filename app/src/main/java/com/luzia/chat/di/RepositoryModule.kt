package com.luzia.chat.di

import com.luzia.chat.data.ChatRepositoryImpl
import com.luzia.chat.domain.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URI

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}
