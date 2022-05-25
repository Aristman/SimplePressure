package ru.marslab.simplepressure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.simplepressure.data.RepositoryFirestore
import ru.marslab.simplepressure.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: RepositoryFirestore): Repository
}
