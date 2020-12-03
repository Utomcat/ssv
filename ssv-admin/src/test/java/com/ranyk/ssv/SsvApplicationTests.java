package com.ranyk.ssv;

import com.alibaba.fastjson.JSONObject;
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

    @Test
    void testMethod0(){

        String str0 = "[HttpResponseBody{status:'200', message:'成功', result:[BuildInfo{buildid:'ZY2009065_0', natuName:'1-9幢', natuNo:'1-9', struct:'钢筋混凝土', floors:'2', upFloors:'0', downFloors:'2', regionName:'樊城区', address:'襄阳市樊城区长征路昊天广场1-9幢', sno:'昊天广场', saddno:'null', buildYear:'null', buildStyle:'null', haveElevator:'null', xmxqid:'XY20200053', xmxqmc:'襄阳市肖家台棚户区改造（昊天广场A地块）', dh:'4206061700189', forecastMarkName:'预测'}], attributes:null}]";
        String str1 = "{status:'200', message:'成功', result:[{buildid:'ZY2009065_0', natuName:'1-9幢', natuNo:'1-9', struct:'钢筋混凝土', floors:'2', upFloors:'0', downFloors:'2', regionName:'樊城区', address:'襄阳市樊城区长征路昊天广场1-9幢', sno:'昊天广场', saddno:'null', buildYear:'null', buildStyle:'null', haveElevator:'null', xmxqid:'XY20200053', xmxqmc:'襄阳市肖家台棚户区改造（昊天广场A地块）', dh:'4206061700189', forecastMarkName:'预测'}], attributes:''}";

        JSONObject jsonObject = JSONObject.parseObject(str1);

        System.out.println(jsonObject);


    }

}
