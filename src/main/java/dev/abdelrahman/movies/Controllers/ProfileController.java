package dev.abdelrahman.movies.Controllers;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Controllers.Exceptions.ApiResponse;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.RetrieveUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdatePasswordDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdateUserDTO;
import dev.abdelrahman.movies.Services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    UserService userService;

    @GetMapping
    public  ResponseEntity<ApiResponse<?>> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getName().equals("anonymousUser"))
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized", null));

        User user = userService.findUserByUsername(auth.getName());
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getGender()); 
        return  ResponseEntity.ok(new ApiResponse<>(true, "Record found", retrieveUserDTO));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateProfile(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User Updated", userService.update(updateUserDTO, user.getId())));
    }

    @PutMapping("/update-password")
    public ResponseEntity<ApiResponse<?>> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        
        return ResponseEntity.ok(new ApiResponse<>(true, "Password Updated", userService.updatePassword(updatePasswordDTO, user.getId())));
    }
}
