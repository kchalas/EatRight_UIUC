package api;

import org.scribe.model.Token;
import org.scribe.builder.api.DefaultApi10a;

/**
 * Simple class needed for the Yelp OAuthorization
 */
public class YelpAPIOAuth extends DefaultApi10a {

    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token arg0) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }

}