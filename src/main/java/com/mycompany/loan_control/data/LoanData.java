package com.mycompany.loan_control.data;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.jpa.JpaData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.entities.Loan;
import com.mycompany.loan_control.interfaces.repository.LoanRepository;

public class LoanData extends JpaData<Loan, Long> implements LoanRepository {

  @Inject
  public LoanData(PersistManager persistManager) {
    super(persistManager, Loan.class);
  }
}
