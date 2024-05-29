package com.mycompany.loan_control.data;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.jpa.JpaData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.entities.Role;
import com.mycompany.loan_control.interfaces.repository.RoleRepository;

public class RoleData extends JpaData<Role, Long> implements RoleRepository {

  @Inject
  public RoleData(PersistManager persistManager) {
    super(persistManager, Role.class);
  }
}
