package technologia.eduflex.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.AuthentificationRequest;
import technologia.eduflex.dto.AuthentificationResponse;
import technologia.eduflex.services.authentification.AuthentificationService;
import technologia.eduflex.services.logout.LogoutService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthentificationController {

    private final AuthentificationService service;
    private final LogoutService logoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    AuthentificationResponse authentificate(@RequestBody AuthentificationRequest request) {
        return service.authentificate(request);
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
    }

}
