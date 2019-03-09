package travelmate.demo.Users;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final UsersValidator usersValidator;

    public UsersController(UsersRepository usersRepository, ModelMapper modelMapper, UsersValidator usersValidator){
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.usersValidator = usersValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable String id){

        Users users = usersRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    public ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok().body(usersList);
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody @Valid UsersDto usersDto, Errors errors) {

        Users user = modelMapper.map(usersDto, Users.class);
        Users createdUser = usersRepository.save(user);

        usersValidator.validate(usersDto,errors);

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
