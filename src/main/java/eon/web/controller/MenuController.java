package eon.web.controller;

import eon.domain.Menu;
import eon.util.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {
    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> menu(HttpSession session) {
        return (List<Menu>) session.getAttribute(UserContext.MENUS_IN_SESSION);
    }
}
