package pl.plenczewski.sza1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.plenczewski.sza1.services.LoginCounter;
import pl.plenczewski.sza1.services.MessageConstructor;

import java.security.Principal;

@Controller
public class TestController {

    private MessageConstructor messageConstructor;

    @Autowired
    public TestController(MessageConstructor messageConstructor) {
        this.messageConstructor = messageConstructor;
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(Principal principal) {
        return new ResponseEntity<>(messageConstructor.getMessageForAdmin(principal), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(Principal principal) {
        return new ResponseEntity<>(messageConstructor.getMessageForUser(principal), HttpStatus.OK);
    }

    @GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous(Principal principal) {
        return new ResponseEntity<>(messageConstructor.getMessageForUserDependentByRole(principal), HttpStatus.OK);
    }

    @GetMapping("/bye")
    public ResponseEntity<String> byePage() {
        return new ResponseEntity<>("papa", HttpStatus.OK);
    }
}
