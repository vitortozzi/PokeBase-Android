package br.com.pokebase.data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApolloClientModule {

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphql.pokeapi.co/v1beta2")
            .okHttpClient(okHttpClient = okHttpClient)
            .build()
    }
}