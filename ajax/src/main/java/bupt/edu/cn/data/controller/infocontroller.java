package bupt.edu.cn.data.controller;

import bupt.edu.cn.data.Dao.infoRepository;
import bupt.edu.cn.data.entity.info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class infocontroller {

    public infoRepository info_r;
    @Autowired
    public void setinfoRepository(infoRepository info_r)
    {
        this.info_r = info_r;
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "user",required = false)String user, @RequestParam (value = "psw",required = false)String psw, HttpSession session)
    {
        if (user!=null && psw!=null && user.equals(("123@bupt.edu.cn")) && psw.equals("123456"))
        {
            session.setAttribute("log",true);
            ModelAndView modelAndView = new ModelAndView("contact");
            modelAndView.addObject("cList",info_r.findAll());
            return modelAndView;
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView("login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request){

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String add = request.getParameter("add");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        info person = new info();
        person.address=add;
        person.email=email;
        person.name=name;
        person.qq=qq;
        person.tel=tel;

        info_r.save(person);
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("cList",info_r.findAll());
        return modelAndView;
    }

    @RequestMapping("/todel")
    public ModelAndView del(HttpServletRequest request) {
        String id = request.getParameter("id");
        info_r.deleteById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("cList",info_r.findAll());
        return modelAndView;
    }

    @RequestMapping("/toedit")
    public String toedit(Model model, HttpServletRequest request)
    {
        String id = request.getParameter("id");
        Optional<info> cOptional = info_r.findById(Long.parseLong(id));
        if(cOptional.isPresent()){
            info piece = cOptional.get();
            model.addAttribute("c",piece);
            return "edit";
        }
        else{
            return "failure";
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request) {
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String add = request.getParameter("add");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        info person = new info();
        person.address=add;
        person.email=email;
        person.name=name;
        person.qq=qq;
        person.tel=tel;
        person.id = Long.parseLong(id);
        info_r.save(person);
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("cList",info_r.findAll());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/checktel")
    public String isExist(@RequestParam(name = "tel") String tel) {
        List<info> already = info_r.findByTel(tel);
        return already.isEmpty() ? "false" : "true";
    }

}
