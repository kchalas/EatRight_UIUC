package api;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpAPI {
    private static String apiKey = "yRwLKaDIIXa6Il5u9Fe2tw";
    private static String consumerSecret = "zQAMp01DVxjPsRDqhsgBKAXc-Xc";
    private static String token = "hqqQbpx8ThYQmU5NJVZvx7ssrYLoJips";
    private static String tokenSecret = "bJqymYD0vIOy7CN8aTR4JtvkgHY";

    public static String getRestaurants(double lat, double lon){
        OAuthService service = new ServiceBuilder().provider(YelpAPIOAuth.class).apiKey(apiKey).apiSecret(consumerSecret).build();
        Token accessToken = new Token(token, tokenSecret);

        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");

        request.addQuerystringParameter("term", "food");
        request.addQuerystringParameter("ll", lat + "," + lon);

        service.signRequest(accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
