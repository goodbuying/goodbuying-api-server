package goodbuyning.api_server.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 마켓 링크 타입을 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum LinkType {
    TIKTOK("tiktok", "틱톡"),
    YOUTUBE("youtube", "유튜브"),
    INSTAGRAM("instagram", "인스타그램"),
    BLOG("blog", "블로그"),
    WEBSITE("website", "웹사이트");

    private final String code;
    private final String displayName;
}
