package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordForm {
    private String user_id;
    private String password_actual;
    private String password_nuevo;

    public PasswordForm(String user_id, String password_actual, String password_nuevo) {
        this.user_id = user_id;
        this.password_actual = password_actual;
        this.password_nuevo = password_nuevo;
    }

    public PasswordForm() {
    }
    
}
