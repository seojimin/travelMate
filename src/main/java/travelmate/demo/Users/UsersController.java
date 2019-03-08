package travelmate.demo.Users;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity registration(@RequestBody @Valid UserDto userDto, Errors errors) {

        User user = modelMapper.map(userDto, User.class);
        User createdUser = usersRepository.save(user);

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping
    public ResponseEntity signIn() {

        return ResponseEntity.ok().body(null);
    }




}
