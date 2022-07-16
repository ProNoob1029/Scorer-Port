package com.dragos.scorerport.dependencyInjection

import android.app.Application
import com.dragos.scorerport.App
import com.dragos.scorerport.Database
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
    fun provideDatabase(app: Application): Database {
        return Database(app)
    }

    @Provides
    @Singleton
    fun provideApp(): App {
        return App()
    }

}