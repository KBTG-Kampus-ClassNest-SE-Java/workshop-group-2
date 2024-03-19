<<<<<<<< HEAD:kshop/src/main/java/com/kampus/kshop/controller/HealthController.java
package com.kampus.kshop.controller;
========
package com.kampus.kbazaar.health;
>>>>>>>> 0170fe0 (Rename project kshop to kbazaar):kbazaar/src/main/java/com/kampus/kbazaar/health/HealthController.java

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "{ \"status\": \"alive\" }";
    }
}