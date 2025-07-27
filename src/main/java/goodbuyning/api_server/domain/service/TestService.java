package goodbuyning.api_server.domain.service;

import goodbuyning.api_server.domain.api.request.TestPostReq;
import goodbuyning.api_server.domain.api.response.TestPostRes;
import goodbuyning.api_server.global.common.exception.DomainException;
import goodbuyning.api_server.global.common.response.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public TestPostRes test_1(TestPostReq req){
        if(req.num()  < 5) {
            throw new DomainException(ErrorCode.TEST_INVALID);
        }
        return new TestPostRes("TEST RESPONSE ! CHECK GOGO");
    }
}
