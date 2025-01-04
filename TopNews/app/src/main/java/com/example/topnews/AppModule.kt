package com.example.topnews

import android.content.Context
import androidx.test.espresso.core.internal.deps.dagger.Module
import androidx.test.espresso.core.internal.deps.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.topnews.Repositories.ArticlesAPI
import javax.inject.Singleton
import kotlin.text.Typography.dagger

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext app: Context) : Context{
        return app
    }

    @Provides
    @Singleton
    fun provideArticleApi() : ArticlesAPI {
        return ArticlesAPI
    }
}