package com.ranyk.ssv.common.utils;


import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName:ReflectionUtils
 * Description:反射相关的辅助方法
 *
 * @author ranyi
 * @date 2019-12-17 17:21
 * Version: V1.0
 */
public class ReflectionUtils {

    /**
     * 根据方法名调用指定对象的方法
     * @param object 要调用方法的对象，需调用的Dao接口对象/需查找的MyBatis的映射XML文件
     * @param method 要调用的方法名，需要执行的Dao接口中的方法名称
     * @param args 参数列表数据类型对象数组，执行Dao接口中方法所需要的参数列表的数据类型对象数组？？
     * @return 返回执行完方法的方法返回对象 即方法执行完后的返回对象
     */
    public static Object invoke(@NotNull Object object, String method, Object... args){

        //定义返回结果对象
        Object result = null;
        //获取需要调用的Dao接口的Class对象
        Class<? extends Object> clazz = object.getClass();
        //获取需要执行的Dao接口中的Method对象
        Method queryMethod = getMethod(clazz,method,args);

        //判断是否存在需要执行的方法
        if (queryMethod != null){
            //存在需要执行的方法
            try {
                //执行指定的方法，获取指定的方法执行结果
                result = queryMethod.invoke(object,args);
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            //不存在需要执行的方法
            try {
                //抛出指定的异常
                throw  new NoSuchMethodException(clazz.getName()+"类中没有找到"+method+"方法！");
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }
        }

        //返回执行方法的结果
        return result;
    }

    /**
     * 通过反射，根据Class对象、方法名称和参数列表获取需要执行的Dao接口方法
     *
     * 可能同一个类中存在多个相同名称的方法，判断通过Class对象获取的方法是否为指定的方法的标准为，指定的方法名称一致，对应的方法名称的参数列表的数据类型一样，包括数据类型的位置和数据类型的个数
     *
     * @param clazz 类(Class)对象
     * @param name 需要执行的方法名称
     * @param args 参数列表数据类型对象数组，执行Dao接口中方法所需要的参数列表的数据类型对象数组？？
     * @return 返回对应的方法对象
     */
    private static Method getMethod(@NotNull Class<? extends Object> clazz, String name, Object[] args) {

        //初始定义一个方法对象
        Method queryMethod = null;
        //通过反射，使用class对象调用getMethods方法，获取该类中的所有方法，放置在数组中
        Method[] methods = clazz.getMethods();
        //遍历方法数组，判断获取的方法列表中是否有指定的方法
        for (Method method: methods) {

            //判断方法名称是否是指定的方法名称
            if (method.getName().equals(name)){

                //获取对应方法名称的参数列表的数据类型数组
                Class<?>[] parameterTypes = method.getParameterTypes();

                //判断指定的方法名称的参数列表的数据类型的数组长度是否一致，用于确认是否为一样的方法
                if (parameterTypes.length == args.length) {
                    //定义方法相同变量
                    boolean isSameMethod = true;

                    //循环参数列表的数据类型数组，判断对应位置的数据类型是否一致
                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object arg = args[i];
                        if (arg == null){
                            arg = "";
                        }

                        //数据类型的位置不一致，两个方法不一致
                        if (!parameterTypes[i].equals(args[i].getClass())) {
                            isSameMethod = false;
                        }
                    }

                    //判断此次的方法是否是需要执行的方法
                    if (isSameMethod){
                        queryMethod = method;
                        break;
                    }
                }
            }
        }

        //返回方法对象
        return queryMethod;
    }

}
