package bupt.edu.cn.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class opcontroller {
    @RequestMapping("/toadd")
    public String toadd()
    {
        return "add_contact";
    }




}
