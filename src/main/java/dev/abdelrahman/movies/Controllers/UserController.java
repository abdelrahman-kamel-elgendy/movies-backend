package dev.abdelrahman.movies.Controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

