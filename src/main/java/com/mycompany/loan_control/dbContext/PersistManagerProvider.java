package com.mycompany.loan_control.dbContext;

import com.google.inject.Provider;

public class PersistManagerProvider implements Provider<PersistManager> {

  @Override
  public PersistManager get() {
      return new PersistManager();
  }
}
