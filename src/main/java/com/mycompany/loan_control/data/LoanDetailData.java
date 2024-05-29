package com.mycompany.loan_control.data;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.jpa.JpaData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.entities.LoanDetail;
import com.mycompany.loan_control.interfaces.repository.LoanDetailRepository;

public class LoanDetailData extends JpaData<LoanDetail, Long> implements LoanDetailRepository{

  @Inject
  public LoanDetailData(PersistManager persistManager) {
    super(persistManager, LoanDetail.class);
  }
}
