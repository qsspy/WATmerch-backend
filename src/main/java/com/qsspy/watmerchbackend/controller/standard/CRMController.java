package com.qsspy.watmerchbackend.controller.standard;

import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.model.PaginatorModel;
import com.qsspy.watmerchbackend.service.ICRMService;
import com.qsspy.watmerchbackend.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

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
            @RequestParam(defaultValue = "20") Integer size
    ) {

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

        return "users";
    }

    @GetMapping("/userDetails/{userId}")
    public String getUserDetailsView(
            Model model,
            @PathVariable int userId) throws IOException {

        ShopUser user = userService.getUser(userId);
        byte[] byteImage;
        if(user.getUserDetails().getAvatar() != null) {
            byteImage = user.getUserDetails().getAvatar();
        } else {
            byteImage = Files.readAllBytes(Path.of("src\\main\\resources\\static\\images\\users_basic_images\\blank-user.png"));
        }
        String base64Image = Base64.getMimeEncoder().encodeToString(byteImage);
        model.addAttribute("avatar",base64Image);
        model.addAttribute("user",userService.getUser(userId));
        model.addAttribute("roles",crmService.getRoles());
        return "user-details";
    }

    @PostMapping("/userDetails/{userId}")
    public RedirectView editUserDetailsView(
            @PathVariable long userId,
            @RequestParam Map<String,String> allRequestParams,
            RedirectAttributes attributes) throws IOException {

        userService.editUser(allRequestParams,userId);

        attributes.addFlashAttribute("success", true);
        return new RedirectView("/crm/userDetails/" + userId);
    }

    @PostMapping("/users/delete/{userId}")
    public RedirectView deleteUser(
            @PathVariable Long userId,
            RedirectAttributes attributes
    ) {
        userService.deleteUser(userId);
        attributes.addFlashAttribute("deleted",true);
        return new RedirectView("/crm/users");
    }
}
