package com.mycompany.loan_control.controllers.menuuser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.mycompany.loan_control.App;
import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.entities.response.Response;
import com.mycompany.loan_control.interfaces.impl.BookImpl;
import com.mycompany.loan_control.services.BookService;
import com.mycompany.loan_control.utils.components.BookCard;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;

public class IndexController implements Initializable {

  private BookImpl bookService;  

  public IndexController() {

  }

  @Inject
  public IndexController(BookService bookService) {
    this.bookService = bookService;
  }

  @FXML
  public TilePane container;

  @FXML
  public TextField searchField;

  @FXML
  public Button searchButton;

  private Label searchMessage;

  private Label noResultMessage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    TilePane.setMargin(container, new Insets(10, 10, 10, 10));

    Response<List<Book>> books = bookService.getAllBook();

    if (books.isSuccess())
      books.getData()
          .forEach(book -> container.getChildren()
              .add(new BookCard(new Image(
                  "https://png.pngtree.com/png-clipart/20190921/original/pngtree-open-brown-book-illustration-png-image_4719502.jpg"),
                  book.getName())));
    else
      System.out.println(books.getMessage());

    searchButton.onMouseClickedProperty().set(e -> search());
    searchField.onActionProperty().set(e -> search());
  }

  public void search() {
    String text = searchField.getText();
    if (text.length() >= 3) {
      container.getChildren().clear();
      searchMessage = new Label();
      searchMessage.setText("Cargando...");
      searchMessage.setTextAlignment(TextAlignment.CENTER);
      searchMessage.setWrapText(true);
      searchMessage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000000;");
      container.getChildren().add(searchMessage);

      // Crear y ejecutar un Task para realizar la búsqueda
      Task<Response<List<Book>>> searchTask = new Task<Response<List<Book>>>() {
        @Override
        protected Response<List<Book>> call() throws Exception {
          return bookService.getSearchBooksByName(text);
        }
      };

      searchTask.setOnSucceeded(event -> {
        Response<List<Book>> books = searchTask.getValue();
        container.getChildren().clear();
        if (books.getData().size() > 0) {
          books.getData().forEach(book -> container.getChildren()
              .add(new BookCard(new Image(
                  "https://png.pngtree.com/png-clipart/20190921/original/pngtree-open-brown-book-illustration-png-image_4719502.jpg"),
                  book.getName())));
        } else {
          noResultMessage = new Label();
          noResultMessage.setText("No hay resultados para la búsqueda.");
          noResultMessage.setTextAlignment(TextAlignment.CENTER);
          noResultMessage.setWrapText(true);
          noResultMessage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000000;");
          container.getChildren().add(noResultMessage);
        }
      });

      // Ejecutar el Task en un hilo separado
      Thread searchThread = new Thread(searchTask);
      searchThread.start();
    } 
  }

  @FXML
  public void goToHistory() {    
    App.view("history");
  }

  @FXML
  public void goToBorrowBook() {    
    App.view("borrowbook");
  }

}
