package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.CustomerDetailsService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class SignupController {

    CustomerService customerService;
    RoleService roleService;
    SecurityContextRepository securityContextRepository;
    CustomerDetailsService customerDetailsService;


    //-------------------- Inscription du client/utilisateur--------------------------------
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, @ModelAttribute("customer") Customer customer, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String email = customer.getEmail();
        if (customerService.fetchByEmail(email).isPresent()) {
            model.addAttribute("emailError", true);
            return "signup";
        }

        Optional<Role> customerRole = roleService.findByName("USER");
        if (customerRole.isEmpty()) return "404";
        customer.setRole(customerRole .orElse(null));
        customerService.registerNewCustomer(customer);

        var userDetails = customerDetailsService.loadUserByUsername(customer.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        session.setAttribute("email", customer.getEmail());

        return "redirect:/products";
    }
}
