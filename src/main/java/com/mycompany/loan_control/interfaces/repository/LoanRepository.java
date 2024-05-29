package com.mycompany.loan_control.interfaces.repository;

import com.mycompany.loan_control.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
  
}
