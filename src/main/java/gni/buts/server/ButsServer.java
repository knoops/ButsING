package gni.buts.server;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.contentType;
import static com.xebialabs.restito.semantics.Action.delay;
import static com.xebialabs.restito.semantics.Action.noop;
import static com.xebialabs.restito.semantics.Action.resourceContent;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;
import static com.xebialabs.restito.semantics.Condition.withPostBodyContaining;

import java.io.FileReader;

import com.google.gson.Gson;
import com.xebialabs.restito.semantics.Action;
import com.xebialabs.restito.server.StubServer;

import gni.buts.model.RestStub;

public class ButsServer {

    public static void main(String[] args) throws Exception {

        final StubServer server = new StubServer(8844).run();

        whenHttp(server).match(get("/ping")).then(contentType("application/json"), resourceContent("example1/response1.json"));

        Gson gson = new Gson();
        RestStub restStub =
                gson.fromJson(
                        new FileReader(
                                "C:\\Users\\MU53YL\\workspace_chaptertime\\BustINGv2\\ButsING\\src\\main\\resources\\example1\\request1.json"),
                        RestStub.class);

        String method = restStub.getHttpMethod();

        if ("GET".equals(method)) {
            whenHttp(server).match(get(restStub.getUri())).then(contentType("application/json"), resourceContent(restStub.getResponse()));
        } else if ("POST".equals(method)) {
            whenHttp(server).match(post(restStub.getUri())).then(createResponseAction(restStub), createPostAction(restStub));
        }

        while (true) {
            Thread.sleep(10000);
        }

    }

    private static Action createResponseAction(RestStub restStub) {
        return Action.composite(resourceContent(restStub.getResponse()));

    }

    private static Action createPostAction(RestStub restStub) {
        return Action.composite(delay(restStub.getDelayPeriod()),
                postCallback(restStub.getPostUri(), restStub.getPostBodyAfterDelay()));
    }

    private static Action postCallback(String postUri, String postBodyAfterDelay) {



        return Action.noop();
    }

}
