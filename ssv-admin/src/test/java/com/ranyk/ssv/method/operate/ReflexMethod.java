package com.ranyk.ssv.method.operate;

import com.ranyk.ssv.method.vo.PeopleVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * ClassName:ReflexMethod
 * Description:反射方法
 *
 * @author ranyi
 * @date 2020-12-07 10:20
 * Version: V1.0
 */
@Log4j2
@Component
public class ReflexMethod {

    public void clearVoParameter(PeopleVO vo, String ... attributes) throws NoSuchFieldException, IllegalAccessException {
        if (attributes.length <= 0) {
            log.error("不存在需要清理的属性");
            return;
        }

        if (null == vo ) {
            log.error("需要清理的对象为空,无需进行清理");
            return;
        }

        // 获取指定对象的class对象
        Class<? extends PeopleVO> voClass = vo.getClass();

        for (String attribute : attributes) {
            Field declaredField = voClass.getDeclaredField(attribute);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(attribute);
            if (null == obj || "".equals(obj)) {
                log.error("指定的属性 " + attribute + " 值为空,无需进行清理!");
                continue;
            }

            declaredField.set(vo,null);
        }

    }

}
