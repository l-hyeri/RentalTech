package rentaltech.electronics.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String main() {
        log.info("home controller");
        return "main";
    }

    @RequestMapping("/adminHome")
    public String admin() {
        log.info("admin controller");
        return "adminHome";
    }

    @RequestMapping("/userHome")
    public String user() {
        log.info("user controller");
        return "userHome";
    }
}
