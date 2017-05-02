package gni.buts.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by MU53YL on 2-5-2017.
 */
@Data
public class RestStub implements Serializable{

    private String httpMethod;
    private String uri;
    private String postBody;
    private Map<String, String> params;
    private String response;
    private Integer delayPeriod;
    private String postBodyAfterDelay;
    private String postUri;

}
