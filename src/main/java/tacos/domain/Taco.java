package tacos.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
//@Table(name = "TACO")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP) // Hibernate가 TIMESTAMP와 매핑하도록 지정
    private Date createdAt;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    // size는 null일때는 통과 null이 아닐때의 size를 의미
    @Size(min = 2, message = "you must choose at least 1 ingredient")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TACO_INGREDIENTS", // 조인 테이블 이름
            joinColumns = @JoinColumn(name = "TACO"), // Taco의 기본 키를 참조하는 컬럼
            inverseJoinColumns = @JoinColumn(name = "INGREDIENT") // Ingredient의 기본 키를 참조하는 컬럼
    )
    private List<Ingredient> ingredients;
    // 뷰에서는 스프링 converter사용하기

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}