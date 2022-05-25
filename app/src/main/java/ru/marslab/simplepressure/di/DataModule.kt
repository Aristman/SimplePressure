package ru.marslab.simplepressure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.simplepressure.data.RepositoryImpl
import ru.marslab.simplepressure.domain.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}
