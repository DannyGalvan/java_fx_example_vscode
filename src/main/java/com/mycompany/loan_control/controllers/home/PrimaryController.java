package com.mycompany.loan_control.controllers.home;

import java.io.IOException;
import java.util.List;

import com.google.inject.Inject;
import com.mycompany.loan_control.App;
import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.entities.request.BookRequest;
import com.mycompany.loan_control.entities.response.Response;
import com.mycompany.loan_control.interfaces.impl.BookImpl;
import com.mycompany.loan_control.services.BookService;
import com.mycompany.loan_control.utils.Params;

import javafx.fxml.FXML;

public class PrimaryController {

    private BookImpl bookService;

    public PrimaryController() {
    }

    @Inject
    public PrimaryController(BookService bookService) {
        this.bookService = bookService;
    }

    @FXML
    public void switchToSecondary() throws IOException {

        BookRequest book = new BookRequest();
        book.setName("El principito");
        book.setDescription("El principito es una obra literaria del escritor y aviador francés Antoine de Saint-Exupéry, ilustrada por él mismo. Fue publicado por primera vez en 1943. Es un cuento poético y filosófico que tiene como protagonista a un niño, quien se encuentra con un aviador en el desierto del Sahara. El libro ha sido traducido a cientos de idiomas y ha vendido aproximadamente 140 millones de copias, convirtiéndose en uno de los libros más vendidos de la historia.");
        book.setImage("https://images-na.ssl-images-amazon.com/images/I/71a3J8J7ZXL.jpg");

        Response<Book> response = bookService.addBook(book);

        System.out.println(response);

        Book bookUpdate = response.getData();
        bookUpdate.setName("El principito 2");
        bookUpdate.setDescription("Descripcion de prueba");

        response = bookService.updateBook(bookUpdate);

        System.out.println(response);

        var books = bookService.getSearchBooksByName("El principito 2");

        Params<Response<List<Book>>> paramString = new Params<>();
        paramString.setData(books);
        
        App.setRoot("home", "secondary",(Params<?>) paramString);
    }
}
