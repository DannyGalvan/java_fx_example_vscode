package com.mycompany.loan_control.services;

import java.util.List;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.BookData;
import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.entities.request.BookRequest;
import com.mycompany.loan_control.entities.response.Response;
import com.mycompany.loan_control.interfaces.impl.BookImpl;
import com.mycompany.loan_control.validations.books.CreateBookValidations;
import com.mycompany.loan_control.validations.books.UpdateBookValidations;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

public class BookService implements BookImpl {

  BookData bookRepository;

  @Inject
  public BookService(BookData bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Response<Book> getBook(Long ISBN) {
    Response<Book> response = new Response<Book>();

    try {
      Book book = bookRepository.getById(ISBN);

      if (book == null) {
        response.setData(null);
        response.setSuccess(false);
        response.setMessage("Libro no encontrado");

        return response;
      }

      response.setData(book);
      response.setSuccess(true);
      response.setMessage("Libro encontrado");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al obtener libro: " + e.getMessage());

      return response;
    }
  }

  public Response<List<Book>> getAllBook() {
    Response<List<Book>> response = new Response<List<Book>>();

    try {
      List<Book> books = bookRepository.getAll();

      if (books == null) {
        response.setData(null);
        response.setSuccess(false);
        response.setMessage("No hay libros");

        return response;
      }

      response.setData(books);
      response.setSuccess(true);
      response.setMessage("Libros encontrados");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al obtener libros: " + e.getMessage());

      return response;
    }
  }

  public Response<List<Book>> getSearchBooksByName(String search) {
    Response<List<Book>> response = new Response<List<Book>>();

    try {
      List<Book> books = bookRepository.getSearchBooksByName(search);

      if (books == null) {
        response.setData(null);
        response.setSuccess(false);
        response.setMessage("No hay libros");

        return response;
      }

      response.setData(books);
      response.setSuccess(true);
      response.setMessage("Libros encontrados");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al obtener libros: " + e.getMessage());

      return response;
    }
  }

  public Response<Book> addBook(BookRequest book) {
    Response<Book> response = new Response<Book>();
    Validator<BookRequest> validatorBook = new CreateBookValidations();

    try {
      ValidationResult result = validatorBook.validate(book);

      if (!result.isValid()) {
        response.setData(null);
        response.setSuccess(false);
        response.setMessage("Error al agregar libro");
        response.setErrors(result.getErrors());

        return response;
      }

      Book newBook = new Book();
      newBook.setName(book.getName());
      newBook.setDescription(book.getDescription());
      newBook.setImage(book.getImage());

      if (!bookRepository.save(newBook)) {
        response.setSuccess(false);
        response.setMessage("Error al agregar libro");

        return response;
      }

      response.setData(newBook);
      response.setSuccess(true);
      response.setMessage("Libro agregado correctamente");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al agregar libro: " + e.getMessage());

      return response;
    }
  }

  public Response<Book> updateBook(Book book) {
    Response<Book> response = new Response<Book>();
    Validator<Book> validatorBook = new UpdateBookValidations(bookRepository);

    try {

      ValidationResult result = validatorBook.validate(book);

      if (!result.isValid()) {
        response.setData(null);
        response.setSuccess(false);
        response.setMessage("Error al actualizar libro");
        response.setErrors(result.getErrors());

        return response;
      }

      if (!bookRepository.update(book)) {
        response.setSuccess(false);
        response.setMessage("Error al actualizar libro");

        return response;
      }

      response.setData(book);
      response.setSuccess(true);
      response.setMessage("Libro actualizado correctamente");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al actualizar libro: " + e.getMessage());

      return response;
    }
  }

  public Response<Book> deleteBook(Long ISBN) {

    Response<Book> response = new Response<Book>();

    try {
      Book book = bookRepository.getById(ISBN);

      if (!bookRepository.delete(ISBN)) {
        response.setSuccess(false);
        response.setMessage("Error al eliminar libro");
        return response;
      }

      response.setData(book);
      response.setSuccess(true);
      response.setMessage("Libro eliminado correctamente");

      return response;
    } catch (Exception e) {
      response.setData(null);
      response.setSuccess(false);
      response.setMessage("Error al eliminar libro: " + e.getMessage());

      return response;
    }
  }
}
