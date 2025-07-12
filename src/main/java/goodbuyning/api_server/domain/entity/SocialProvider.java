package goodbuyning.api_server.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 소셜 로그인 제공업체를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum SocialProvider {
    KAKAO("kakao", "카카오"),
    NAVER("naver", "네이버"),
    GOOGLE("google", "구글"),
    APPLE("apple", "애플");

    private final String code;
    private final String displayName;
}
