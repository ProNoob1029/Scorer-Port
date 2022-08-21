package com.dragos.scorerport.di

import android.app.Application
import androidx.room.Room
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.data.data_source.MatchDatabase
import com.dragos.scorerport.feature_editor.data.repository.RepositoryImpl
import com.dragos.scorerport.feature_editor.domain.repository.Repository
import com.dragos.scorerport.feature_editor.domain.use_case.*
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
    fun provideRoomDatabase(app: ScorerApp): MatchDatabase {
        return Room.databaseBuilder(
            app,
            MatchDatabase::class.java,
            MatchDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: MatchDatabase): Repository {
        //return ListRepositoryImpl(firebaseDatabase)
        return RepositoryImpl(db.matchDao)
    }

    @Provides
    @Singleton
    fun provideListUseCases(repository: Repository): MatchUseCases {
        return MatchUseCases(
            getMatchList = GetMatchList(repository),
            getMatch = GetMatch(repository),
            saveMatch = SaveMatch(repository),
            getMatchFlow = GetMatchFlow(repository)
        )
    }

    @Provides
    @Singleton
    fun provideApp(application: Application): ScorerApp {
        return application as ScorerApp
    }
}