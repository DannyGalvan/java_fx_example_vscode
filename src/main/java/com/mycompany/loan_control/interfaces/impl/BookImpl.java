package com.mycompany.loan_control.interfaces.impl;

import java.util.List;

import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.entities.request.BookRequest;
import com.mycompany.loan_control.entities.response.Response;

public interface BookImpl {
  Response<Book> getBook(Long ISBN);

  Response<List<Book>> getAllBook();

  Response<List<Book>> getSearchBooksByName(String search);

  Response<Book> addBook(BookRequest book);

  Response<Book> updateBook(Book book);

  Response<Book> deleteBook(Long ISBN);
}
