package johnson.zhang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello(HttpServletRequest request){
//        String code = request.getHeader("exchange-cloud");
        return "hello world";
    }

    @GetMapping("/")
    public String loclhost8801(HttpServletRequest request){
//        String code = request.getHeader("exchange-cloud");
        return "hello world";
    }
}
