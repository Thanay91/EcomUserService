package org.example.ecom_userservice.controllerAdvisor;

import org.example.ecom_userservice.dtos.SignUpResponseDTO;
import org.example.ecom_userservice.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<SignUpResponseDTO> handleUserAlreadyExistException(UserAlreadyExistException e){
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        signUpResponseDTO.setMessage(e.getMessage());
        signUpResponseDTO.setErrorThrownLocation("This error is thrown from global level");
        ResponseEntity responseEntity = new ResponseEntity(signUpResponseDTO, HttpStatus.FORBIDDEN);
        return responseEntity;
    }
}
