package tacos.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createAt;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    // size는 null일때는 통과 null이 아닐때의 size를 의미
    @Size(min = 2, message = "you must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
    // 뷰에서는 스프링 converter사용하기

    @PrePersist
    void createdAt() {
        this.createAt = new Date();
    }
}