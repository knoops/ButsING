package gni.buts.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by MU53YL on 2-5-2017.
 */
@Getter
@Setter
public class RestStub {

    private String httpMethod;
    private String uri;
    private String postBody;
    private Map<String, String> params;
    private String response;
    private String delayPeriod;
    private String postBodyAfterDelay;
    private String postUri;

}
