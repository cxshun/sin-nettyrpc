package sin.nettyrpc.example.server;

import sin.nettyrpc.server.RpcServer;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class ServerTest {

    public static void main(String[] args) throws InterruptedException {
        new RpcServer().start();
    }

}
