package pattara.phuteeka.neversitup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pattara.phuteeka.neversitup.data.repository.DepartmentRepository
import pattara.phuteeka.neversitup.data.repository.DepartmentRepositoryImpl
import pattara.phuteeka.neversitup.data.repository.ImageRepository
import pattara.phuteeka.neversitup.data.repository.ImageRepositoryImpl


@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryBindModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsDepartmentRepository(
        departmentRepositoryImpl: DepartmentRepositoryImpl
    ): DepartmentRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsImageRepository(
        imageRepositoryImpl:ImageRepositoryImpl
    ):ImageRepository
}
