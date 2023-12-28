package com.example.project_1;

import com.example.project_1.models.ContactData;
import com.example.project_1.models.ContactInfo;
import com.example.project_1.models.LoginInfo;
import com.example.project_1.models.SignUpInfo;
import com.example.project_1.models.UserData;
import com.example.project_1.repositories.ContactDataRepo;
import com.example.project_1.repositories.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebsiteController {
    @Autowired
    private UserDataRepo userDataRepo;
    @Autowired
    private ContactDataRepo contactDataRepo;

    @GetMapping(value = "/loginPage")
    public String loginPage(final Model model) {
        model.addAttribute("loginInfo", new LoginInfo());
        model.addAttribute("signUpInfo", new SignUpInfo());

        return "index";
    }

    @GetMapping(value = "/mainPage")
    public String mainPage(final Model model) {
        return "Home";
    }

    @PostMapping(value = "/signIn")
    public String signIn(@ModelAttribute("signUpInfo") SignUpInfo signUpInfo, final Model model) {
        System.out.println(signUpInfo.toString());
        if(userDataRepo.findById(signUpInfo.getUsername()).isEmpty()) {
            userDataRepo.save(new UserData(signUpInfo.getUsername(), signUpInfo.getPassword()));

            return "redirect:/mainPage";
        }

        return "redirect:/loginPage";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo, final Model model) {
        System.out.println(loginInfo.toString());

        if(userDataRepo.findById(loginInfo.getUsername()).isPresent()) {
            return "redirect:/mainPage";
        }

        return "redirect:/loginPage";
    }

    @GetMapping(value = "/contactPage")
    public String contactPage(final Model model) {
        model.addAttribute("contactInfo", new ContactInfo());
        return "Contact";
    }

    @PostMapping(value = "/sendContactInfo")
    public String sendContactInfo(@ModelAttribute("contactInfo") ContactInfo contactInfo, final Model model) {
        final ContactData contactData = new ContactData();

        contactData.setName(contactInfo.getName());
        contactData.setEmail(contactInfo.getEmail());
        contactData.setMessage(contactInfo.getMessage());

        System.out.println(contactData.toString());

        contactDataRepo.save(contactData);

        return "redirect:/contactPage";
    }
}
