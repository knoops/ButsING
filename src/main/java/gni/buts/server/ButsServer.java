package gni.buts.server;

import com.xebialabs.restito.server.StubServer;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.contentType;
import static com.xebialabs.restito.semantics.Action.resourceContent;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.get;

public class ButsServer {

    public static void main(String[] args) throws Exception {

        final StubServer server = new StubServer(8844).run();

        //whenHttp(server).match(get("/ping")).then(contentType("text/html"), stringContent("Pong!"));


        whenHttp(server).match(get("/ping")).then(contentType("text/html"), stringContent("Pong!"));


        
        while (true) { Thread.sleep(10000); }

    }

}
