package pl.plenczewski.sza1.services;

import java.security.Principal;

public interface MessageConstructor {
    String getMessageForUserDependentByRole(Principal principal);

    String getMessageForUser(Principal principal);

    String getMessageForAdmin(Principal principal);
}
