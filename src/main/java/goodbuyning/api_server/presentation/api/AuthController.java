package goodbuyning.api_server.presentation.api;

import goodbuyning.api_server.domain.entity.enums.SocialProvider;
import goodbuyning.api_server.domain.service.AuthService;
import goodbuyning.api_server.global.common.response.ApiResponse;
import goodbuyning.api_server.global.common.response.ErrorCode;
import goodbuyning.api_server.presentation.api.response.AuthLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sellers/oauth/{provider}")
    public ResponseEntity<ApiResponse<String>> getOauthLoginUrl(@PathVariable SocialProvider provider) {
        String res = authService.getKakaoOAuthUrl();
        return ResponseEntity.ok(ApiResponse.success(res));
    }

    @GetMapping("/sellers/oauth/kakao/callback")
    public ResponseEntity<ApiResponse<AuthLoginRes>> kakaoCallback(@RequestParam(required = false) String code,
                                                                   @RequestParam(required = false) String error,
                                                                   @RequestParam(required = false) String error_description) {
        if (error != null) {
            return ResponseEntity.badRequest().body(ApiResponse.failure(ErrorCode.UNAUTHORIZED));
        }

        try {
            AuthLoginRes res = authService.processKakaoLogin(code, error, error_description);
            return ResponseEntity.ok(ApiResponse.success(res));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.failure(ErrorCode.UNAUTHORIZED));
        }
    }
}
