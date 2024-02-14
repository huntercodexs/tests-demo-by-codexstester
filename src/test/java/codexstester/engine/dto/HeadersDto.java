package codexstester.engine.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HeadersDto {
    String contentType;
    String accepted;
    String httpMethod;
    String statusCode;
    String crossOrigin;
    String origin;
    String hostname;
    String ip;
    String osName;
    String authorizationBasic;
    String authorizationBearer;
    String apiKeyToken;
    String apiKeyAppName;
    String apiKeySecret;
    String apiKeyValue;
    String apiKeyGeneric;
    String additionalName;
    String additionalValue;
    Map<String,String> bodyParameters;
}
