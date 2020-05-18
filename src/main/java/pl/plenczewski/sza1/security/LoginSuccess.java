package pl.plenczewski.sza1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import pl.plenczewski.sza1.services.LoginCounter;

@Component
public class LoginSuccess implements ApplicationListener<AuthenticationSuccessEvent> {

    private LoginCounter loginCounter;

    @Autowired
    public LoginSuccess(LoginCounter loginCounter) {
        this.loginCounter = loginCounter;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        loginCounter.incrementCounter(authenticationSuccessEvent.getAuthentication().getName());
    }
}
