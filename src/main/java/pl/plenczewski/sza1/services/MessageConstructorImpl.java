package pl.plenczewski.sza1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class MessageConstructorImpl implements MessageConstructor {

    private static final String LOGIN_COUNTER_MESSAGE = " Zalogowales sie juz: ";

    private LoginCounter loginCounter;

    @Autowired
    public MessageConstructorImpl(LoginCounter loginCounter) {
        this.loginCounter = loginCounter;
    }

    @Override
    public String getMessageForUserDependentByRole(Principal principal) {
        if (principal != null) {
            return "Czesc " + principal.getName();
        }
        return "Czesc nieznajomy";
    }

    @Override
    public String getMessageForUser(Principal principal) {
        return "Hello user " + principal.getName() + LOGIN_COUNTER_MESSAGE + loginCounter.getCountByUsername(principal.getName());
    }

    @Override
    public String getMessageForAdmin(Principal principal) {
        return "Hello admin " + principal.getName() + LOGIN_COUNTER_MESSAGE + loginCounter.getCountByUsername(principal.getName());
    }
}
