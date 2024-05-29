package com.mycompany.loan_control.interfaces.repository;

import java.util.List;

import com.mycompany.loan_control.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

  List<Book> getSearchBooksByName(String search);  
}
