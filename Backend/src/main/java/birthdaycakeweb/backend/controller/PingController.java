package birthdaycakeweb.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PingController {
    @GetMapping("/api/ping")
    public Map<String, Boolean> ping() {
        return Map.of("ok", true);
    }
}
