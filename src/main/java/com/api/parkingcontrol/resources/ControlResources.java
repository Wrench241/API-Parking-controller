package com.api.parkingcontrol.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlResources {

    @GetMapping("/")
    public String text(){
        return "caraio";
    }
}
