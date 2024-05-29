package com.mycompany.loan_control.validations.books;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.mycompany.loan_control.entities.request.BookRequest;

import br.com.fluentvalidator.AbstractValidator;

public class CreateBookValidations extends AbstractValidator<BookRequest> {
  
  @Override
  public void rules() {

    ruleFor(BookRequest::getName)
      .must(not(stringEmptyOrNull()))
        .withMessage("El nombre es requerido")
        .withFieldName("Name")          
        .critical()
      .must(stringSizeBetween(3, 50))
        .withMessage("El nombre debe tener al menos 3 caracteres y maximo 50")
        .withFieldName("Name")
        .critical();
    
    ruleFor(BookRequest::getDescription)
      .must(not(stringEmptyOrNull()))
        .withMessage("La descripción es requerida")
        .withFieldName("Description")
        .critical()
      .must(stringSizeBetween(10, 500))
        .withMessage("La descripción debe tener al menos 10 caracteres y maximo 100")
        .withFieldName("Description")
        .critical();

    ruleFor(BookRequest::getImage)
      .must(not(stringEmptyOrNull()))
        .withMessage("La imagen es requerida")
        .withFieldName("Image")
        .critical();
  }
}
