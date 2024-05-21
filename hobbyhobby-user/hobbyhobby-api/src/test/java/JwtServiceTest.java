
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.v1.model.User;
import org.v1.global.config.security.jwt.JwtService;
import org.v1.global.config.security.jwt.JwtToken;
import io.jsonwebtoken.JwtException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {

    private final String accessSecretKey = "testsecretkeydaslkjslsdnmflsdkgjsldgkjlweptojkgn";
    private final String refreshSecretKey = "testsecretkeydaslkjslsdnmflsdkgjsldgkjlweptojkgn";
    private final JwtToken jwtToken = new JwtToken(accessSecretKey, 100, refreshSecretKey, 100);
    private final JwtService jwtService = new JwtService(jwtToken);

    @Test
    @DisplayName("access token 생성에 성공")
    void successAccessTokenCreate() {
        //given
        User user = createUser();

        //when
        String token = jwtService.generateAccessToken(String.valueOf(user.getId()),user.getNickname());
        String tokenUserId = jwtService.extractAccessUserId(token);

        //then
        assertTrue(jwtService.isAccessTokenValid(token));
        assertEquals(String.valueOf(tokenUserId),tokenUserId);
    }

    @Test
    @DisplayName("refresh token 생성에 성공")
    void successRefreshTokenCreate() {
        //given
        Long userId = 1L;
        User user = createUser();

        //when
        String token = jwtService.generateRefreshToken(String.valueOf(user.getId()),user.getNickname());
        String tokenUserId = jwtService.extractRefreshUserId(token);

        //then
        assertTrue(jwtService.isAccessTokenValid(token));
        assertEquals(String.valueOf(tokenUserId),tokenUserId);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 1000})
    @DisplayName("유효한 토큰 검증")
    void successValidTokenEvaluation(int expirationSecond) {
        //given
        JwtToken jwtConfig = new JwtToken(accessSecretKey, expirationSecond, refreshSecretKey, 100);
        JwtService jwtService = new JwtService(jwtConfig);

        //when
        User user = createUser();
        String token = jwtService.generateAccessToken(String.valueOf(user.getId()),user.getNickname());

        //given
        boolean result = jwtService.isAccessTokenValid(token);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    @DisplayName("만료된 토큰 검증")
    void successExpiredTokenEvaluation(int expirationSecond) {
        //given
        JwtToken jwtConfig = new JwtToken(accessSecretKey, expirationSecond, refreshSecretKey, expirationSecond);
        JwtService jwtService = new JwtService(jwtConfig);

        //when
        User user = createUser();
        String generateAccessToken = jwtService.generateAccessToken(String.valueOf(user.getId()),user.getNickname());
        String generateRefreshToken = jwtService.generateRefreshToken(String.valueOf(user.getId()),user.getNickname());
        //then
        Assertions.assertThrows(JwtException.class, ()-> {
            jwtService.isAccessTokenValid(generateAccessToken);
        });
        Assertions.assertThrows(JwtException.class, ()-> {
            jwtService.isRefreshTokenValid(generateRefreshToken);
        });
    }
    User createUser(){
        return  User.withId(
                new User.UserId(1L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "testDeviceToken",
                "123",
                "testName",
                '01011112222',
                "2001/12/16"
        );
    }
}