package com.codeafrica.markeplace.exceptions;

public class CartItemNotExistException extends Exception{
    public CartItemNotExistException(String msg){
        super(msg);
    }
}
