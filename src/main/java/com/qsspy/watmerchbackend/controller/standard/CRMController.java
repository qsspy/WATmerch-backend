package com.qsspy.watmerchbackend.controller.standard;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.model.PaginatorModel;
import com.qsspy.watmerchbackend.service.ICRMService;
import com.qsspy.watmerchbackend.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/crm")
public class CRMController {

    private IUserService userService;
    private ICRMService crmService;

    public CRMController(IUserService userService, ICRMService crmService) {
        this.userService = userService;
        this.crmService = crmService;
    }

    @GetMapping("/users")
    public String getUsersView(
            Model model,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") Integer roleId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "1") Integer size
    ) {

        System.out.println("keyword: " + keyword);
        System.out.println("roleId: " + roleId);
        System.out.println("page: " + page);
        System.out.println("size: " + size);

        if(keyword == null) {
            keyword = "";
        }

        Page<ShopUser> pageOfUsers = userService.getUsers(page-1,size,keyword, roleId);

        model.addAttribute("roleId", roleId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("users",pageOfUsers.getContent());
        model.addAttribute("roles",crmService.getRoles());
        model.addAttribute("size",size);
        model.addAttribute("page",page);
        PaginatorModel paginator = crmService.getPageIndexes(page,pageOfUsers.getTotalPages());
        model.addAttribute("paginator",paginator);
        model.addAttribute("pageSizes",crmService.getPageSizes());

        System.out.println(paginator.getPages());
        return "users";
    }

    @GetMapping("/userDetails/{userId}")
    public String getUserDetailsView(
            Model model,
            @PathVariable int userId)
    {

        model.addAttribute("user",userService.getUser(userId));
        return "user-details";
    }
}
