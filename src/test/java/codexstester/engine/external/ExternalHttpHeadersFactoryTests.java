package codexstester.engine.external;

import codexstester.engine.dto.*;
import codexstester.engine.internal.InternalHttpHeadersFactoryTests;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public abstract class ExternalHttpHeadersFactoryTests extends InternalHttpHeadersFactoryTests {

    protected ExternalHttpHeadersFactoryTests(String target) {
        super(target);
    }

    protected HttpComponentsClientHttpRequestFactory externalHttpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    protected HttpHeaders externalBuilderHeaders(RequestDto requestDto, HeadersDto headersDto) {

        HttpHeaders headers = new HttpHeaders();

        /*
         * From properties file
         */
        if (externalAuthorizationBasic != null && !externalAuthorizationBasic.equals("")) {
            headers.set("Authorization", "Basic " + externalAuthorizationBasic.replaceFirst("Basic ", ""));
        }
        if (externalAuthorizationBearer != null && !externalAuthorizationBearer.equals("")) {
            headers.set("Authorization", "Bearer " + externalAuthorizationBearer.replaceFirst("Bearer ", ""));
        }
        if (externalTokenAuthorization != null && !externalTokenAuthorization.equals("")) {
            headers.set("Api-Key-Token", externalTokenAuthorization);
        }
        if (externalAppNameAuthorization != null && !externalAppNameAuthorization.equals("")) {
            headers.set("Api-Key-App-Name", externalAppNameAuthorization);
        }
        if (externalSecretAuthorization != null && !externalSecretAuthorization.equals("")) {
            headers.set("Api-Key-Secret", externalSecretAuthorization);
        }
        if (externalValueAuthorization != null && !externalValueAuthorization.equals("")) {
            headers.set("Api-Key-Value", externalValueAuthorization);
        }
        if (externalGenericAuthorization != null && !externalGenericAuthorization.equals("")) {
            headers.set("Api-Key-Generic", externalGenericAuthorization);
        }
        if (externalAdditionalHeaderName1 != null && !externalAdditionalHeaderName1.equals("")) {
            if (externalAdditionalHeaderValue1 != null && !externalAdditionalHeaderValue1.equals("")) {
                headers.set(externalAdditionalHeaderName1, externalAdditionalHeaderValue1);
            }
        }
        if (externalAdditionalHeaderName2 != null && !externalAdditionalHeaderName2.equals("")) {
            if (externalAdditionalHeaderValue2 != null && !externalAdditionalHeaderValue2.equals("")) {
                headers.set(externalAdditionalHeaderName2, externalAdditionalHeaderValue2);
            }
        }
        if (externalAdditionalHeaderName3 != null && !externalAdditionalHeaderName3.equals("")) {
            if (externalAdditionalHeaderValue3 != null && !externalAdditionalHeaderValue3.equals("")) {
                headers.set(externalAdditionalHeaderName3, externalAdditionalHeaderValue3);
            }
        }
        if (externalAdditionalHeaderName4 != null && !externalAdditionalHeaderName4.equals("")) {
            if (externalAdditionalHeaderValue4 != null && !externalAdditionalHeaderValue4.equals("")) {
                headers.set(externalAdditionalHeaderName4, externalAdditionalHeaderValue4);
            }
        }
        if (externalAdditionalHeaderName5 != null && !externalAdditionalHeaderName5.equals("")) {
            if (externalAdditionalHeaderValue5 != null && !externalAdditionalHeaderValue5.equals("")) {
                headers.set(externalAdditionalHeaderName5, externalAdditionalHeaderValue5);
            }
        }

        /*
         * From HeadersDto Class (Overwrite above headers)
         */
        if (headersDto.getAuthorizationBasic() != null && !headersDto.getAuthorizationBasic().equals("")) {
            headers.set("Authorization", "Basic " + headersDto.getAuthorizationBasic().replaceFirst("Basic ", ""));
        }
        if (headersDto.getAuthorizationBearer() != null && !headersDto.getAuthorizationBearer().equals("")) {
            headers.set("Authorization", "Bearer " + headersDto.getAuthorizationBearer().replaceFirst("Bearer ", ""));
        }
        if (headersDto.getApiKeyToken() != null && !headersDto.getApiKeyToken().equals("")) {
            headers.set("Api-Key-Token", headersDto.getApiKeyToken());
        }
        if (headersDto.getApiKeyAppName() != null && !headersDto.getApiKeyAppName().equals("")) {
            headers.set("Api-Key-App-Name", headersDto.getApiKeyAppName());
        }
        if (headersDto.getApiKeySecret() != null && !headersDto.getApiKeySecret().equals("")) {
            headers.set("Api-Key-Secret", headersDto.getApiKeySecret());
        }
        if (headersDto.getApiKeyValue() != null && !headersDto.getApiKeyValue().equals("")) {
            headers.set("Api-Key-Value", headersDto.getApiKeyValue());
        }
        if (headersDto.getApiKeyGeneric() != null && !headersDto.getApiKeyGeneric().equals("")) {
            headers.set("Api-Key-Generic", headersDto.getApiKeyGeneric());
        }
        if (headersDto.getAdditionalName() != null && !headersDto.getAdditionalName().equals("")) {
            if (headersDto.getAdditionalValue() != null && !headersDto.getAdditionalValue().equals("")) {
                headers.set(headersDto.getAdditionalName(), headersDto.getAdditionalValue());
            }
        }

        /*Default Headers*/
        if (headersDto.getContentType() != null && !headersDto.getContentType().equals("")) {
            headers.set("Content-Type", headersDto.getContentType());
        } else {
            headers.set("Content-Type", "application/json;charset=UTF-8");
        }

        /*Default Headers*/
        if (headersDto.getAccepted() != null && !headersDto.getAccepted().equals("")) {
            headers.set("Accept", headersDto.getAccepted());
        } else {
            headers.set("Accept", "application/json;charset=UTF-8");
        }

        return headers;
    }

    protected static ResponseEntity<Oauth2ResponseTokenDto> codexsTesterExternalOAuth2GetToken(Oauth2RequestTokenDto oauth2RequestTokenDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Basic " + oauth2RequestTokenDto.getAuth().replaceFirst("Basic ", ""));
        String credentials = "?username="+ oauth2RequestTokenDto.getUser()+"&password="+ oauth2RequestTokenDto.getPass()+"&grant_type="+ oauth2RequestTokenDto.getGrant();
        HttpEntity<String> httpEntity = new HttpEntity<>(credentials, httpHeaders);
        return genericRestTemplate.postForEntity(oauth2RequestTokenDto.getUrl() + credentials, httpEntity, Oauth2ResponseTokenDto.class);
    }

    protected static ResponseEntity<Object> codexsTesterExternalOAuth2CheckToken(Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Basic " + oauth2RequestCheckTokenDto.getAuthorization().replaceFirst("Basic ", ""));

        if (oauth2RequestCheckTokenDto.getAddtionalName() != null && !oauth2RequestCheckTokenDto.getAddtionalName().equals("")) {
            if (oauth2RequestCheckTokenDto.getAddtionalValue() != null && !oauth2RequestCheckTokenDto.getAddtionalValue().equals("")) {
                httpHeaders.set(oauth2RequestCheckTokenDto.getAddtionalName(), oauth2RequestCheckTokenDto.getAddtionalValue());
            }
        }

        String body = "?token="+ oauth2RequestCheckTokenDto.getToken().replaceFirst("Bearer ", "");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);
        return genericRestTemplate.postForEntity(oauth2RequestCheckTokenDto.getUrl() + body, httpEntity, Object.class);
    }

}
