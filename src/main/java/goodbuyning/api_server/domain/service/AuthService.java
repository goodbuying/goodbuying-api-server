package goodbuyning.api_server.domain.service;

import goodbuyning.api_server.global.common.exception.AuthorizationException;
import goodbuyning.api_server.global.common.response.ErrorCode;
import goodbuyning.api_server.infra.generator.KakaoGenerator;
import goodbuyning.api_server.infra.generator.TokenGenerator;
import goodbuyning.api_server.presentation.api.response.AuthLoginRes;
import goodbuyning.api_server.presentation.api.response.KakaoTokenRes;
import goodbuyning.api_server.presentation.api.response.KakaoUserInfoRes;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getKakaoOAuthUrl() {
        return KakaoGenerator.generateOAuthUrl();
    }

    public AuthLoginRes processKakaoLogin(String authorizationCode, String error, String errorDescription) {
        KakaoTokenRes tokenResponse = KakaoGenerator.getAccessToken(authorizationCode);
        KakaoUserInfoRes userInfo = KakaoGenerator.getUserInfo(tokenResponse.accessToken());

        String userId = String.valueOf(userInfo.id());
        String jwtAccessToken = TokenGenerator.generateAccessToken(userId);
        String jwtRefreshToken = TokenGenerator.generateRefreshToken(userId);

        if(error != null) {
            throw new AuthorizationException(ErrorCode.UNAUTHORIZED);
        }

        return new AuthLoginRes(jwtAccessToken, jwtRefreshToken, "seller", userInfo.id());
    }
}
