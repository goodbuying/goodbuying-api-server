package goodbuyning.api_server.infra.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenGeneratorTest {

    @Autowired
    private TokenGenerator tokenGenerator;

    private String userId;

    @BeforeEach
    void setUp() {
        userId = "testUser123";
    }

    @Test
    @DisplayName("액세스 토큰 생성 - 성공")
    void generateAccessToken_Success() {
        // given
        String testUserId = userId;

        // when
        String accessToken = tokenGenerator.generateAccessToken(testUserId);

        // then
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isNotEmpty();
        assertThat(accessToken.split("\\.")).hasSize(3); // JWT는 3개 부분으로 구성
    }

    @Test
    @DisplayName("리프레시 토큰 생성 - 성공")
    void generateRefreshToken_Success() {
        // given
        String testUserId = userId;

        // when
        String refreshToken = tokenGenerator.generateRefreshToken(testUserId);

        // then
        assertThat(refreshToken).isNotNull();
        assertThat(refreshToken).isNotEmpty();
        assertThat(refreshToken.split("\\.")).hasSize(3); // JWT는 3개 부분으로 구성
    }

    @Test
    @DisplayName("동일한 사용자 ID로 생성한 토큰들은 서로 다름")
    void generateTokens_SameUserId_DifferentTokens() {
        // given
        String testUserId = userId;

        // when
        String accessToken1 = tokenGenerator.generateAccessToken(testUserId);
        String accessToken2 = tokenGenerator.generateAccessToken(testUserId);
        String refreshToken1 = tokenGenerator.generateRefreshToken(testUserId);
        String refreshToken2 = tokenGenerator.generateRefreshToken(testUserId);

        // then
        assertThat(accessToken1).isNotEqualTo(accessToken2);
        assertThat(refreshToken1).isNotEqualTo(refreshToken2);
        assertThat(accessToken1).isNotEqualTo(refreshToken1);
    }
}