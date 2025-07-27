package goodbuyning.api_server.global.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import goodbuyning.api_server.global.common.response.ApiResponse;
import goodbuyning.api_server.global.common.response.ErrorCode;
import goodbuyning.api_server.infra.validator.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final TokenValidator tokenValidator;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            writeErrorResponse(response, ErrorCode.MISSING_AUTHORIZATION_HEADER);
            return false;
        }

        String token = authorizationHeader.substring(7);
        
        if (!tokenValidator.validateToken(token)) {
            writeErrorResponse(response, ErrorCode.INVALID_TOKEN);
            return false;
        }

        if (tokenValidator.isTokenExpired(token)) {
            writeErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
            return false;
        }

        String userId = tokenValidator.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        
        return true;
    }
    
    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws Exception {
        response.setStatus(errorCode.getHttpStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        
        ApiResponse<Void> apiResponse = ApiResponse.failure(errorCode);
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}