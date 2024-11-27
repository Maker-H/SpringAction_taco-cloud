package tacos.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotBlank(message = "name is required")
    private String deliveryName;

    @NotBlank(message = "street is required")
    private String deliveryStreet;

    @NotBlank(message = "city is required")
    private String deliveryCity;

    @NotBlank(message = "state is required")
    private String deliveryState;

    @NotBlank(message = "zip code is required")
    private String deliveryZip;

//    @CreditCardNumber(message = "not a valid credit card number")
    // 속성의 값이 룬 알고리즘 검사에 합격한 유효한 신용카드 번호어야 함, 그러나 실제로 해당 번호가 존재하는지는 검사하지 못함
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
//             message = "must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "invalid cvv")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
