package com.airtribe.project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
        @GetMapping("/")
        public String welcome() {
            return "News API is running!";
        }

}
