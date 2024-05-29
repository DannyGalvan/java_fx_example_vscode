package com.mycompany.loan_control;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.mycompany.loan_control.data.BookData;
import com.mycompany.loan_control.data.LoanData;
import com.mycompany.loan_control.data.LoanDetailData;
import com.mycompany.loan_control.data.RoleData;
import com.mycompany.loan_control.data.UserData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.dbContext.PersistManagerProvider;
import com.mycompany.loan_control.interfaces.impl.BookImpl;
import com.mycompany.loan_control.interfaces.repository.BookRepository;
import com.mycompany.loan_control.interfaces.repository.LoanDetailRepository;
import com.mycompany.loan_control.interfaces.repository.LoanRepository;
import com.mycompany.loan_control.interfaces.repository.RoleRepository;
import com.mycompany.loan_control.interfaces.repository.UserRepository;
import com.mycompany.loan_control.services.BookService;

public class AppModule extends AbstractModule {

  @Override
  protected void configure() {

    //dbcontext
   bind(PersistManager.class).toProvider(new PersistManagerProvider()).in(Scopes.SINGLETON);

    //respositories
    bind(RoleRepository.class).to(RoleData.class).in(Scopes.NO_SCOPE);
    bind(UserRepository.class).to(UserData.class).in(Scopes.NO_SCOPE);
    bind(BookRepository.class).to(BookData.class).in(Scopes.NO_SCOPE);
    bind(LoanRepository.class).to(LoanData.class).in(Scopes.NO_SCOPE);
    bind(LoanDetailRepository.class).to(LoanDetailData.class).in(Scopes.NO_SCOPE);
    
    //services
    bind(BookImpl.class).to(BookService.class).in(Scopes.NO_SCOPE);
  }
}
