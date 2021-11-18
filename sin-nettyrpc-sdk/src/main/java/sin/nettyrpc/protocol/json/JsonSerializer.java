package sin.nettyrpc.protocol.json;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import sin.nettyrpc.protocol.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/18
 **/
public class JsonSerializer implements Serializer {
    @Override
    public <T> void serialize(T obj, ByteBuf byteBuf) {
        byteBuf.writeBytes(JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8));
    }
}
