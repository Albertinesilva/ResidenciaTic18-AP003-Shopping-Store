package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class UserSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Email(message = "Formato do e-mail está inválido", regexp = "^[a-zA-Z0-9._%+-]+_?@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENTE;

    @CreatedDate
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @LastModifiedDate
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @CreatedBy
    @Column(name = "creator_by")
    private String creatorBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN")
    private boolean active;

    @Column(name = "code_verifier", length = 200)
    private String codeverifier;

    public boolean hasNotId() {
        return id == null;
    }

    public boolean hasId() {
        return id != null;
    }

    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserSystem usuario = (UserSystem) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + '}';
    }
}
