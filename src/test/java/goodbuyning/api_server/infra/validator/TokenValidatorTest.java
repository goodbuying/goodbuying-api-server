package goodbuyning.api_server.infra.validator;

import goodbuyning.api_server.infra.generator.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenValidatorTest {

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private TokenGenerator tokenGenerator;

    private String userId;
    private String validToken;

    @BeforeEach
    void setUp() {
        userId = "testUser123";
        validToken = tokenGenerator.generateAccessToken(userId);
    }

    @Test
    @DisplayName("유효한 토큰 검증 - 성공")
    void validateToken_ValidToken_ReturnsTrue() {
        // given
        String token = validToken;

        // when
        boolean isValid = tokenValidator.validateToken(token);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("잘못된 토큰 검증 - 실패")
    void validateToken_InvalidToken_ReturnsFalse() {
        // given
        String invalidToken = "invalid.jwt.token";

        // when
        boolean isValid = tokenValidator.validateToken(invalidToken);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("null 토큰 검증 - 실패")
    void validateToken_NullToken_ReturnsFalse() {
        // given
        String nullToken = null;

        // when
        boolean isValid = tokenValidator.validateToken(nullToken);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("빈 토큰 검증 - 실패")
    void validateToken_EmptyToken_ReturnsFalse() {
        // given
        String emptyToken = "";

        // when
        boolean isValid = tokenValidator.validateToken(emptyToken);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("토큰에서 사용자 ID 추출 - 성공")
    void getUserIdFromToken_ValidToken_ReturnsUserId() {
        // given
        String token = validToken;

        // when
        String extractedUserId = tokenValidator.getUserIdFromToken(token);

        // then
        assertThat(extractedUserId).isEqualTo(userId);
    }

    @Test
    @DisplayName("유효한 토큰 만료 여부 확인 - 만료되지 않음")
    void isTokenExpired_ValidToken_ReturnsFalse() {
        // given
        String token = validToken;

        // when
        boolean isExpired = tokenValidator.isTokenExpired(token);

        // then
        assertThat(isExpired).isFalse();
    }

    @Test
    @DisplayName("잘못된 토큰 만료 여부 확인 - 만료된 것으로 처리")
    void isTokenExpired_InvalidToken_ReturnsTrue() {
        // given
        String invalidToken = "invalid.jwt.token";

        // when
        boolean isExpired = tokenValidator.isTokenExpired(invalidToken);

        // then
        assertThat(isExpired).isTrue();
    }
}