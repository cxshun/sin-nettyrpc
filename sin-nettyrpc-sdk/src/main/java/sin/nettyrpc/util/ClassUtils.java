package sin.nettyrpc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * util for load class
 * user: cxshun@gmail.com
 * date: 2021/11/16
 **/
public class ClassUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * invoke the class's method, if existed
     * @param className className
     * @param methodName    methodName in class
     * @param args  args when calling method
     * @return  return after calling method
     */
    public static Object invoke(String className, String methodName, Object... args) {
        Object obj = null;
        try {
            obj = SpringUtils.getBean(Class.forName(className));
        } catch (ClassNotFoundException e) {
            LOGGER.error(String.format("can not find class:[%s] to load", className), e);
            return null;
        }

        // invoke specify methodName
        Method[] methods = obj.getClass().getMethods();
        Method extractMethod = Arrays.stream(methods)
                .filter(method -> method.getName().equals(methodName) && method.getParameterCount() == args.length)
                .findFirst()
                .orElse(null);
        if (extractMethod == null) {
            LOGGER.error("class:[{}] has not method:[{}]", className, methodName);
            return null;
        }

        try {
            return extractMethod.invoke(obj, args);
        } catch (IllegalAccessException|InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}
