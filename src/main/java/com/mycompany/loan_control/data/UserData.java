package com.mycompany.loan_control.data;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.jpa.JpaData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.entities.User;
import com.mycompany.loan_control.interfaces.repository.UserRepository;

public class UserData extends JpaData<User, Long> implements UserRepository {

  @Inject
  public UserData(PersistManager persistManager) {
    super(persistManager, User.class);
  }

}
