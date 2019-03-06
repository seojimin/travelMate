package travelmate.demo.Users;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersController(UsersRepository usersRepository, ModelMapper modelMapper){
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody UserDto userDto, Errors errors) {
        User user = modelMapper.map(userDto, User.class);
        User createdUser = usersRepository.save(user);

        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping
    public ResponseEntity login() {

        return ResponseEntity.ok().body(null);
    }

}
