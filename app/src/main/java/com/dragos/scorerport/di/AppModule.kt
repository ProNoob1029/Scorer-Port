package com.dragos.scorerport.di

import android.app.Application
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.data.data_source.FirebaseDatabase
import com.dragos.scorerport.feature_editor.data.repository.MatchListRepositoryImpl
import com.dragos.scorerport.feature_editor.domain.repository.MatchListRepository
import com.dragos.scorerport.feature_editor.domain.use_case.ChangeLocation
import com.dragos.scorerport.feature_editor.domain.use_case.GetMatchList
import com.dragos.scorerport.feature_editor.domain.use_case.MatchListUseCases
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
    fun provideDatabase(scorerApp: ScorerApp): FirebaseDatabase {
        return FirebaseDatabase(scorerApp)
    }

    @Provides
    @Singleton
    fun provideMatchListRepository(firebaseDatabase: FirebaseDatabase): MatchListRepository {
        return MatchListRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideMatchListUseCases(repository: MatchListRepository): MatchListUseCases {
        return MatchListUseCases(
            getMatchList = GetMatchList(repository),
            changeLocation = ChangeLocation(repository),
        )
    }

    @Provides
    @Singleton
    fun provideApp(application: Application): ScorerApp {
        return application as ScorerApp
    }
}