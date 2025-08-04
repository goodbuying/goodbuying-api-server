package goodbuyning.api_server.domain.service;

import goodbuyning.api_server.infra.generator.KakaoGenerator;
import goodbuyning.api_server.infra.generator.TokenGenerator;
import goodbuyning.api_server.presentation.api.response.AuthLoginRes;
import goodbuyning.api_server.presentation.api.response.KakaoTokenRes;
import goodbuyning.api_server.presentation.api.response.KakaoUserInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final KakaoGenerator kakaoGenerator;
    private final TokenGenerator tokenGenerator;

    public String getKakaoOAuthUrl() {
        return kakaoGenerator.generateOAuthUrl();
    }
    
    public AuthLoginRes processKakaoLogin(String authorizationCode) {
        KakaoTokenRes tokenResponse = kakaoGenerator.getAccessToken(authorizationCode);
        KakaoUserInfoRes userInfo = kakaoGenerator.getUserInfo(tokenResponse.accessToken());
        
        String userId = String.valueOf(userInfo.id());
        String jwtAccessToken = tokenGenerator.generateAccessToken(userId);
        String jwtRefreshToken = tokenGenerator.generateRefreshToken(userId);
        
        return new AuthLoginRes(
                jwtAccessToken,
                jwtRefreshToken,
                "seller",
                userInfo.id()
        );
    }
}
