package sin.nettyrpc.util;

import java.util.Map;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class ConfigUtils {

    /**
     * load specify key from map
     * @param map   map
     * @param key   key
     * @return  load key from specify map, the key can contains multiple layer
     */
    public static Object getOrDefault(Map<String, Object> map, String key, Object defaultVal) {
        String[] keys = key.split("\\.");
        Object obj = null;
        for (String tempKey:keys) {
            obj = map.get(tempKey);
            if (obj instanceof Map) {
                map = (Map<String, Object>)map.get(tempKey);
            }
        }

        if (obj == null) {
            return defaultVal;
        }
        return obj;
    }

}
