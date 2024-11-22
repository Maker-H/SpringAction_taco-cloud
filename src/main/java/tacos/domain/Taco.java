package tacos.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createAt;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    // size는 null일때는 통과 null이 아닐때의 size를 의미
    @Size(min = 2, message = "you must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}

/*
          <input name="ingredients" type="checkbox" th:value="${ingredient.id}"/>
            <input type="text" th:field="*{name}"/>
          에서 input name의 ingredients -> 체크 박스 여러개 체크 할 수 있음
          과 th:text의 name 속성이 타코의 name으로 붙는다고..?
 */