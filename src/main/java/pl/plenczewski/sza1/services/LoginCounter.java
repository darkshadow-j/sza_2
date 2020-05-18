package pl.plenczewski.sza1.services;

import java.security.Principal;

public interface LoginCounter {
    void incrementCounter(String username);
    Integer getCountByUsername(String username);
}
