package tacos.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data //게터 새터, 생성자 등의 메서드를 자동 생성하라고 알려주는 롬복 애너테이션
@RequiredArgsConstructor //Data를 사용해서 사실 사용할 필요는 없지만 생성자와 관련된 동작의 의도 나타내기 위함
// final로 선언된 필드와 nonnull이 붙은 필드만 포함하는 생성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
public class Ingredient {
     @Id
     private final String id;
     private final String name;
     private final Type type;

     public static enum Type {
         WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
     }
}
