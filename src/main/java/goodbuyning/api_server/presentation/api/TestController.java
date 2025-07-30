package goodbuyning.api_server.presentation.api;

import goodbuyning.api_server.presentation.api.request.TestPostReq;
import goodbuyning.api_server.presentation.api.response.TestPostRes;
import goodbuyning.api_server.domain.service.TestService;
import goodbuyning.api_server.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> ping() {
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<?>> test(@RequestBody TestPostReq req) {
        TestPostRes res = testService.test_1(req);
        return ResponseEntity.ok(ApiResponse.success(res));
    }
}
