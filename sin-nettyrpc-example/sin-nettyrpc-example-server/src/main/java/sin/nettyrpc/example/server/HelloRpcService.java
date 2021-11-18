package sin.nettyrpc.example.server;

import org.springframework.stereotype.Component;
import sin.nettyrpc.example.HelloRpc;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
@Component
public class HelloRpcService implements HelloRpc {
    @Override
    public String hello(String name) {
        return "hello:" + name;
    }
}
