package com.dadvani.libraryApp.controller.admin;

import com.dadvani.libraryApp.dto.UserDto;
import com.dadvani.libraryApp.exceptions.PatronWasNotFoundException;
import com.dadvani.libraryApp.models.User;
import com.dadvani.libraryApp.service.Iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/addPatron")
    public String addNewPatron(Model model, UserDto userDto) {
        model.addAttribute("patron", userDto);
        return "addPatron";
    }
    @PostMapping("/addPatron")
    public String addedPatronSave(@ModelAttribute("patron") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "addPatron";
        }
        userService.save(userDto);
        return "redirect:/addPatron?success";
    }
    @GetMapping("/admin-home/editPatron/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("patron", user);
            model.addAttribute("pageTitle", "Edit Patron (ID: " + id + ")");
            return "editPatron";
        }catch (PatronWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry Patron was deleted from library");
            return "redirect:/admin-home";
        }
    }

    @PostMapping("/admin-home/editPatron/{id}")
    public String saveEditedPatron(@PathVariable("id") Integer id, UserDto userDto,RedirectAttributes ra){
        try {
            User user = userService.get(id);
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setMembershipStatus(userDto.isMembershipStatus());
            userService.saveB(user);
            return "redirect:/admin-home/editPatron/{id}?success";
        } catch (PatronWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry Patron was deleted from library");
            return "redirect:/admin-home";
        }

    }

    @GetMapping("/admin-home/deletePatron/{id}")
    public String deletePatron(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.get(id);
            userService.delete(id);
            ra.addFlashAttribute("message", "patron has been deleted.");
            return "redirect:/admin-home";
        }catch (PatronWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry Patron was deleted from library");
        }
        return "redirect:/admin-home";
    }
}
