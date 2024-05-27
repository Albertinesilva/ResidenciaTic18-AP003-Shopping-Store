package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Scores")
public class Score {

    @Column(name = "score")
    private Integer value;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
