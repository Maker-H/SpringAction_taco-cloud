package tacos.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.text.Format;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @NotNull(message = "user id is required")
    private final String id;

    @NotNull(message = "user password is required")
    private final String pwd;

    @Override
    public String toString() {
        return "[user] id: " + id + "pwd: " + pwd;
    }
}
