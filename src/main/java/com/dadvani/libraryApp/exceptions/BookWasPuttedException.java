package com.dadvani.libraryApp.exceptions;

public class BookWasPuttedException extends Exception{
    public BookWasPuttedException(String errorMsg){
        super(errorMsg);
    }
}
