package gni.buts.server;

import com.google.gson.Gson;
import com.xebialabs.restito.server.StubServer;
import gni.buts.model.RestStub;

import java.io.FileReader;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.*;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;
import static com.xebialabs.restito.semantics.Condition.withPostBodyContaining;

public class ButsServer {

    public static void main(String[] args) throws Exception {

        final StubServer server = new StubServer(8844).run();

        //whenHttp(server).match(get("/ping")).then(contentType("text/html"), stringContent("Pong!"));

        Gson gson = new Gson();
        RestStub restStub = gson.fromJson(new FileReader("C:\\Users\\MU53YL\\workspace_chaptertime\\BustINGv2\\ButsING\\src\\main\\resources\\example1\\request1.json"), RestStub.class);

        Integer delayPeriod = restStub.getDelayPeriod();
        String method = restStub.getHttpMethod();
        String uri = restStub.getUri();
        String response = restStub.getResponse();
        String postBody = restStub.getPostBody();
        String postBodyAfterDelay = restStub.getPostBodyAfterDelay();

        if ("GET".equals(method)) {
            whenHttp(server).match(get(uri)).then(contentType("text/html"), stringContent(response));
        } else if ("POST".equals(method) && postBodyAfterDelay == null) {
            whenHttp(server).match(post(uri), withPostBodyContaining(postBody)).then(contentType("text/html"), stringContent(response));
        } else if ("POST".equals(method) && postBodyAfterDelay != null){
            whenHttp(server).match(withPostBodyContaining(postBodyAfterDelay), post(uri)).then(contentType("text/html"), delay(delayPeriod), stringContent(response));
        }

        
        while (true) { Thread.sleep(10000); }

    }

}
