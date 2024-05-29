package com.mycompany.loan_control.validations.books;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.mycompany.loan_control.data.BookData;
import com.mycompany.loan_control.entities.Book;

import br.com.fluentvalidator.AbstractValidator;

public class UpdateBookValidations extends AbstractValidator<Book> {
  private BookData bookRepository;

  public UpdateBookValidations(BookData bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void rules() {

    ruleFor(Book::getIsbn)
      .must(not(nullValue()))
        .withMessage("El ISBN es requerido")
        .withFieldName("ISBN")
        .critical()
      .must(not(this::ISBNnotExist))
        .withMessage("El ISBN no existe")
        .withFieldName("ISBN")
        .critical();

    ruleFor(Book::getName)
      .must(not(stringEmptyOrNull()))
        .withMessage("El nombre es requerido")
        .withFieldName("Name")          
        .critical()
      .must(stringSizeBetween(3, 50))
        .withMessage("El nombre debe tener al menos 3 caracteres y maximo 50")
        .withFieldName("Name")
        .critical();
    
    ruleFor(Book::getDescription)
      .must(not(stringEmptyOrNull()))
        .withMessage("La descripción es requerida")
        .withFieldName("Description")
        .critical()
      .must(stringSizeBetween(10, 500))
        .withMessage("La descripción debe tener al menos 10 caracteres y maximo 100")
        .withFieldName("Description")
        .critical();

    ruleFor(Book::getImage)
      .must(not(stringEmptyOrNull()))
        .withMessage("La imagen es requerida")
        .withFieldName("Image")
        .critical();
  }

  private boolean ISBNnotExist(Long isbn) {
    boolean exist = bookRepository.getById(isbn) == null;
    return exist;
  }
}
