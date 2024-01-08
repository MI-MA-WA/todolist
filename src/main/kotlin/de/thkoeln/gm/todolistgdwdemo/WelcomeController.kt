package de.thkoeln.gm.todolistgdwdemo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WelcomeController {
    @GetMapping("/")
    @ResponseBody
    fun index(): String {
        return "This Spring App works"
    }
}