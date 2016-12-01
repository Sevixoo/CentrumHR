package com.centrumhr.desktop.di;

import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.domain.IWorkFunctionRepository;
import com.centrumhr.data.importer.EmployeeImporter;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_PrivateEmployeeImporterFactory implements Factory<EmployeeImporter> {
  private final DataBaseModule module;
  private final Provider<IDepartmentRepository> mDepartmentRepositoryProvider;
  private final Provider<IEmployeeRepository> mEmployeeRepositoryProvider;
  private final Provider<IWorkFunctionRepository> mWorkFunctionRepositoryProvider;
  private final Provider<IEmployeeDepartmentRepository> employeeDepartmentRepositoryProvider;

  public DataBaseModule_PrivateEmployeeImporterFactory(DataBaseModule module, Provider<IDepartmentRepository> mDepartmentRepositoryProvider, Provider<IEmployeeRepository> mEmployeeRepositoryProvider, Provider<IWorkFunctionRepository> mWorkFunctionRepositoryProvider, Provider<IEmployeeDepartmentRepository> employeeDepartmentRepositoryProvider) {  
    assert module != null;
    this.module = module;
    assert mDepartmentRepositoryProvider != null;
    this.mDepartmentRepositoryProvider = mDepartmentRepositoryProvider;
    assert mEmployeeRepositoryProvider != null;
    this.mEmployeeRepositoryProvider = mEmployeeRepositoryProvider;
    assert mWorkFunctionRepositoryProvider != null;
    this.mWorkFunctionRepositoryProvider = mWorkFunctionRepositoryProvider;
    assert employeeDepartmentRepositoryProvider != null;
    this.employeeDepartmentRepositoryProvider = employeeDepartmentRepositoryProvider;
  }

  @Override
  public EmployeeImporter get() {  
    EmployeeImporter provided = module.privateEmployeeImporter(mDepartmentRepositoryProvider.get(), mEmployeeRepositoryProvider.get(), mWorkFunctionRepositoryProvider.get(), employeeDepartmentRepositoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<EmployeeImporter> create(DataBaseModule module, Provider<IDepartmentRepository> mDepartmentRepositoryProvider, Provider<IEmployeeRepository> mEmployeeRepositoryProvider, Provider<IWorkFunctionRepository> mWorkFunctionRepositoryProvider, Provider<IEmployeeDepartmentRepository> employeeDepartmentRepositoryProvider) {  
    return new DataBaseModule_PrivateEmployeeImporterFactory(module, mDepartmentRepositoryProvider, mEmployeeRepositoryProvider, mWorkFunctionRepositoryProvider, employeeDepartmentRepositoryProvider);
  }
}

