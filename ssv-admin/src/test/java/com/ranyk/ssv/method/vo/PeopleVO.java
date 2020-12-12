package com.ranyk.ssv.method.vo;

import lombok.*;

/**
 * ClassName:TestVO<br/>
 * Description:测试实体类
 *
 * @author ranyi
 * @date 2020-12-07 10:17
 * Version: V1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PeopleVO {

    private String name;
    private Integer age;
    private String sex;

}
