package pl.plenczewski.sza1.services;

import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;

@Service
public class LoginCounterImpl implements LoginCounter {

    private HashMap<String, Integer> counter;

    public LoginCounterImpl() {
        counter = new HashMap<String, Integer>();
    }


    @Override
    public void incrementCounter(String username) {
        Integer count = counter.get(username);
        if (count != null) {
            count++;
            counter.put(username, count);
        } else {
            counter.put(username, 1);
        }
    }

    @Override
    public Integer getCountByUsername(String username) {
        return counter.get(username);
    }
}
