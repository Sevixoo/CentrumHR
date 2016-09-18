package com.centrumhr.desktop.threading;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public enum AsyncExecutor_Factory implements Factory<AsyncExecutor> {
INSTANCE;

  @Override
  public AsyncExecutor get() {  
    return new AsyncExecutor();
  }

  public static Factory<AsyncExecutor> create() {  
    return INSTANCE;
  }
}

