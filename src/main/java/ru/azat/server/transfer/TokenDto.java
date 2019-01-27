package ru.azat.server.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.azat.server.models.Token;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String value;

    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }
}

