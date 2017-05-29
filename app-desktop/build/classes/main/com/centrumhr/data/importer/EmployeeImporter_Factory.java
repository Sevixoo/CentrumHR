package com.centrumhr.data.importer;

import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmployeeImporter_Factory implements Factory<EmployeeImporter> {
  private final Provider<IORMLiteDataBase> dataBaseProvider;

  public EmployeeImporter_Factory(Provider<IORMLiteDataBase> dataBaseProvider) {  
    assert dataBaseProvider != null;
    this.dataBaseProvider = dataBaseProvider;
  }

  @Override
  public EmployeeImporter get() {  
    return new EmployeeImporter(dataBaseProvider.get());
  }

  public static Factory<EmployeeImporter> create(Provider<IORMLiteDataBase> dataBaseProvider) {  
    return new EmployeeImporter_Factory(dataBaseProvider);
  }
}

