package sin.nettyrpc.protocol.json;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import sin.nettyrpc.protocol.Deserializer;

import java.nio.charset.StandardCharsets;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/18
 **/
public class JsonDeserializer implements Deserializer {
    @Override
    public <T> T deserialize(ByteBuf byteBuf, Class<T> clazz) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), clazz);
    }
}
