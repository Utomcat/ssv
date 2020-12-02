package com.ranyk.ssv.constent;

/**
 * ClassName:TestVO
 * Description:测试枚举对象
 *
 * @author ranyi
 * @date 2020-11-10 9:50
 * Version: V1.0
 */
public enum TestVO implements BaseValueVO{
    /**
     * 测试值一
     */
    TEST_ONE("AAA"),
    TEST_TWO("bbb");


    private final String value;

    TestVO(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
