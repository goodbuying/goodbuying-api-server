package goodbuyning.api_server.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 소셜 로그인 제공업체를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum SocialProvider {
    KAKAO("kakao", "카카오");

    private final String code;
    private final String displayName;
}
