package codexstester.engine.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Oauth2ResponseTokenDto {
    String access_token;
    String refresh_token;
    String scope;
    String token_type;
    String expires_in;
}
