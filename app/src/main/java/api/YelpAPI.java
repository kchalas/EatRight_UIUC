package api;

import android.os.AsyncTask;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpAPI extends AsyncTask<Double, Void, String> {
    private String apiKey = "yRwLKaDIIXa6Il5u9Fe2tw";
    private String consumerSecret = "zQAMp01DVxjPsRDqhsgBKAXc-Xc";
    private String token = "hqqQbpx8ThYQmU5NJVZvx7ssrYLoJips";
    private String tokenSecret = "bJqymYD0vIOy7CN8aTR4JtvkgHY";

    @Override
    protected String doInBackground(Double... coords){
        OAuthService service = new ServiceBuilder().provider(YelpAPIOAuth.class).apiKey(apiKey).apiSecret(consumerSecret).build();
        Token accessToken = new Token(token, tokenSecret);

        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");

        request.addQuerystringParameter("term", "food");
        request.addQuerystringParameter("category_filter", "hotdogs");
        request.addQuerystringParameter("ll", coords[0] + "," + coords[1]);
        request.addQuerystringParameter("limit", "18");

        service.signRequest(accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
