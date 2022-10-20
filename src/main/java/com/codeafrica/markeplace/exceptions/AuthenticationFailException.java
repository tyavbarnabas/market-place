package com.codeafrica.markeplace.exceptions;

public class AuthenticationFailException extends Exception{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
