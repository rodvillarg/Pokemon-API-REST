package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex){
        List<String> detalles = new ArrayList<>();

        for(FieldError erro: ex.getBindingResult().getFieldErrors()){
            detalles.add(erro.getField() + ": " + erro.getDefaultMessage());
        }

        ErrorDTO errorDTO = new ErrorDTO("Error en la validacion de datos de entrada", detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);//400
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarRecursoNoEncontrado(ResourceNotFoundException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO("Recurso no encontrado", detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND); //404
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarExceptionGlobarGenerica(Exception ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getLocalizedMessage());

        ErrorDTO errorDTO = new ErrorDTO("Ocurrio un error interno en el servidor.", detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR); //500
    }
}
