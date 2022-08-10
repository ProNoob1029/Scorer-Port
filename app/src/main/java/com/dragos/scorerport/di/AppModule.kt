package com.dragos.scorerport.di

import android.app.Application
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.MatchModel
import com.dragos.scorerport.feature_editor.MatchScreen
import com.dragos.scorerport.feature_editor.MatchState
import com.dragos.scorerport.feature_editor.data.data_source.FirebaseDatabase
import com.dragos.scorerport.feature_editor.data.repository.TestRepositoryImpl
import com.dragos.scorerport.feature_editor.domain.model.ItemState
import com.dragos.scorerport.feature_editor.domain.repository.ListRepository
import com.dragos.scorerport.feature_editor.domain.use_case.ChangeListLocation
import com.dragos.scorerport.feature_editor.domain.use_case.GetList
import com.dragos.scorerport.feature_editor.domain.use_case.ListUseCases
import com.dragos.scorerport.feature_editor.presentation.edit.Screen
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
    fun provideListRepository(firebaseDatabase: FirebaseDatabase): ListRepository {
        //return ListRepositoryImpl(firebaseDatabase)
        return TestRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideListUseCases(listRepository: ListRepository): ListUseCases {
        return ListUseCases(
            getList = GetList(listRepository),
            changeListLocation = ChangeListLocation(listRepository),
        )
    }

    @Provides
    @Singleton
    fun provideApp(application: Application): ScorerApp {
        return application as ScorerApp
    }

    @Provides
    @Singleton
    fun provideItemState(): ItemState {
        return MatchState(MatchModel())
    }

    @Provides
    @Singleton
    fun provideScreen(): Screen {
        return MatchScreen()
    }
}