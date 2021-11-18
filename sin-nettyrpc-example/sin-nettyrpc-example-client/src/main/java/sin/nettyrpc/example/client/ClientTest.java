package sin.nettyrpc.example.client;

import sin.nettyrpc.example.HelloRpc;
import sin.nettyrpc.client.RpcServiceFactory;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class ClientTest {

    public static void main(String[] args) {
        HelloRpc helloRpc = new RpcServiceFactory().getClass(HelloRpc.class);
        System.out.println(helloRpc.hello("shun"));
    }

}
