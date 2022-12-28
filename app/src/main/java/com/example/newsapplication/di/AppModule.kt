package com.example.newsapplication.di

import com.example.newsapplication.data.remoteconfig.ConfigManager
import com.example.newsapplication.data.source.network.NewsAPI
import com.example.newsapplication.db.ArticleDatabase
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.data.repository.NewsRepositoryImpl
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(firebaseRemoteConfigSettings: FirebaseRemoteConfigSettings): FirebaseRemoteConfig {
        return FirebaseRemoteConfig
            .getInstance().apply {
                setConfigSettingsAsync(firebaseRemoteConfigSettings)
            }
    }

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfigSetting() : FirebaseRemoteConfigSettings {
        return FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewsAPI,
        articleDatabase: ArticleDatabase,
        configManager: ConfigManager
    ) : NewsRepository{
        return NewsRepositoryImpl(newsAPI,articleDatabase,configManager)
    }

}