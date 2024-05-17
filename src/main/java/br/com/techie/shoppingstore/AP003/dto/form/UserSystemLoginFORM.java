package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemLoginFORM {

    @NotNull
    @Email(message = "Formato do e-mail está inválido")
    private String username;

    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN")
    private boolean active;

    public UserSystemLoginFORM(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
