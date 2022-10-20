package com.codeafrica.markeplace.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
