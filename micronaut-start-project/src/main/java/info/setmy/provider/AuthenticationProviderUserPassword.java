package info.setmy.provider;

import io.micronaut.http.HttpRequest;
/*
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
*/
import io.reactivex.Flowable;
import java.util.ArrayList;
import org.reactivestreams.Publisher;

public class AuthenticationProviderUserPassword /*implements AuthenticationProvider*/ {

    /*@Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        if (authenticationRequest.getIdentity().equals("user") && authenticationRequest.getSecret().equals("password")) {
            return Flowable.just(new UserDetails("user", new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
    }*/

}
