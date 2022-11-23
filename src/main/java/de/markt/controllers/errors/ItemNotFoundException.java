package de.markt.controllers.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
 public class ItemNotFoundException extends RuntimeException { 
         private static final long serialVersionUID = 1L; 
  
         public ItemNotFoundException(String message) { 
                 super(message); 
         } 
 }
