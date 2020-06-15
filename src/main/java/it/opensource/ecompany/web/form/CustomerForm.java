package it.opensource.ecompany.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerForm {
    
    private String firstname;
    
    private String lastname;

    private String username;

    private String password;

    private String password2;
    
    private String email;
}
