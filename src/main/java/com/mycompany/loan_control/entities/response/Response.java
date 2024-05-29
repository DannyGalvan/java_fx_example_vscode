package com.mycompany.loan_control.entities.response;

import java.util.Collection;

import br.com.fluentvalidator.context.Error;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response<T> {
    private boolean success;
    private String message;
    private T data;
    private Collection<Error> errors;
    
}
