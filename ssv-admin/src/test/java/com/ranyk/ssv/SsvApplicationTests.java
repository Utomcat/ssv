package com.ranyk.ssv;

import com.ranyk.ssv.constent.TestVO;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log
@SpringBootTest
class SsvApplicationTests {


    @Test
    void contextLoads() {
        for (TestVO value : TestVO.values()) {
            log.info(value.toString());
        }

    }

}
