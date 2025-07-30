package goodbuyning.api_server.presentation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/sellers/oauth/{provider}")
    public ResponseEntity<?> getOauthLoginUrl(@PathVariable String provider) {
        return ResponseEntity.ok(provider);
    }

    @GetMapping("/sellers/oauth/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam(required = false) String code,
                                           @RequestParam(required = false) String error,
                                           @RequestParam(required = false) String error_description) {
        return ResponseEntity.ok(null);
    }
}
