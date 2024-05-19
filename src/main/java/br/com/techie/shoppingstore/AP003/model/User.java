package br.com.techie.shoppingstore.AP003.model;

import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String username;

    private String password;

    private String passwordConfirm;

    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private RoleEnum role = RoleEnum.ROLE_CLIENT;

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

    @Column(name = "code_verifier", length = 200)
    private String codeVerifier;

    public boolean hasNotId() {
        return id == null;
    }

    public boolean hasId() {
        return id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + '}';
    }
}
