package codexstester.test.external;

import codexstester.engine.dto.Oauth2RequestTokenDto;
import codexstester.engine.dto.Oauth2ResponseTokenDto;
import codexstester.setup.bridge.TestsDemoBridgeTests;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static codexstester.engine.security.SecurityTests.codexsTesterSecurityOAuth2Token;

public class TestsDemoExternalTests extends TestsDemoBridgeTests {

    public String oauth2Token() {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    @Test
    public void propsTest() {
        System.out.println(externalProps);
    }

}
