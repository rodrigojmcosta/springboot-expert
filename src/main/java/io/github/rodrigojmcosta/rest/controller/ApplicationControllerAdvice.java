package io.github.rodrigojmcosta.rest.controller;

import io.github.rodrigojmcosta.exception.RegraNegocioException;
import io.github.rodrigojmcosta.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    /*
    Captura erros em tempo de execução para que se faça tratamento personalizado de erros, definindo
    status HTTP específicos e capturando a mensagem de cada erro
     */
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

}
