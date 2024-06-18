package org.example.ecom_userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpEventDTO {
    private String to;
    private String from;
    private String subject;
    private String body;
}
