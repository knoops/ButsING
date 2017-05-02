package gni.buts.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;


@Data
public class RestStub implements Serializable {

    private String httpMethod;
    private String uri;
    private String postBody;
    private Map<String, String> params;
    private String response;
    private Integer delayPeriod;
    private String postBodyAfterDelay;
    private String postUri;

}
