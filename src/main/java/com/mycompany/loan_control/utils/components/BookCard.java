package com.mycompany.loan_control.utils.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BookCard extends AnchorPane {
  private ImageView bookImageView;
  private Label bookNameLabel;

  public BookCard(Image image, String bookName) {
      // Configurar la imagen del libro
      bookImageView = new ImageView(image);
      bookImageView.setFitWidth(190);
      bookImageView.setFitHeight(180);
      bookImageView.setPreserveRatio(true);

      AnchorPane.setTopAnchor(bookImageView, 0.0);
      AnchorPane.setRightAnchor(bookImageView, 0.0);
      AnchorPane.setLeftAnchor(bookImageView, 0.0);

      // Configurar el nombre del libro
      bookNameLabel = new Label(bookName);
      bookNameLabel.setFont(Font.font("Thonburi Bold", 16));
      bookNameLabel.setAlignment(Pos.CENTER);
      AnchorPane.setTopAnchor(bookNameLabel, 170.0);
      AnchorPane.setRightAnchor(bookNameLabel, 0.0);
      AnchorPane.setLeftAnchor(bookNameLabel, 0.0);

      this.setBorder(Border.stroke(Color.BLACK));
      this.setPrefSize(200, 200);

      // Agregar elementos a la tarjeta
      getChildren().addAll(bookImageView, bookNameLabel);
  }
}
