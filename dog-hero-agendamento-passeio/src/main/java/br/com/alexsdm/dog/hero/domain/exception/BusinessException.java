package br.com.alexsdm.dog.hero.domain.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem){
        super(mensagem);
    }
}
