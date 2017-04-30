package com.jdriven.ng2boot;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; //Adicionei isso

@RestController
public class GreetingController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();


    @CrossOrigin //E isso
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Diego") String name) {
        return new Greeting(1,
        			//counter.incrementAndGet(),
                            String.format(name));

    }
}
