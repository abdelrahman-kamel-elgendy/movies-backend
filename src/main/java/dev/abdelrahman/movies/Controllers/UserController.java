package dev.abdelrahman.movies.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Controllers.Crud.CrudController;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.CreateUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.RetrieveUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdateUserDTO;
import dev.abdelrahman.movies.Services.UserService;
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends CrudController<UserService, User, RetrieveUserDTO, CreateUserDTO, UpdateUserDTO> {
}