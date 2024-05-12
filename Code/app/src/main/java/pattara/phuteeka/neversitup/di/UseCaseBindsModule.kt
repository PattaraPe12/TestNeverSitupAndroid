package pattara.phuteeka.neversitup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pattara.phuteeka.neversitup.domain.DepartmentUseCase
import pattara.phuteeka.neversitup.domain.DepartmentUseCaseImpl
import pattara.phuteeka.neversitup.domain.ImageUseCase
import pattara.phuteeka.neversitup.domain.ImageUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseBindsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsDepartmentUseCase(
        getDepartmentUseCaseImpl: DepartmentUseCaseImpl
    ): DepartmentUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsImageUseCase(
        imageUseCaseImpl: ImageUseCaseImpl
    ): ImageUseCase
}
