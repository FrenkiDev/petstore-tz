package endpoints;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endpoint {
    private Map<String, String> urls;
    private Map<String, String> headerParams;
    private Map<String, String> request;
    private Map<String, String> response;
}