package com.dadvani.libraryApp.exceptions;

public class BookWasNotFoundException extends Exception{
    public BookWasNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
