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

    // get single id
    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable String id){
        Users users = usersRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(users);
    }

    // get all the Users
    @GetMapping
    public ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok().body(usersList);
    }

    // save
    @PostMapping
    public ResponseEntity registration(@RequestBody @Valid UsersDto usersDto, Errors errors) {

        Users user = modelMapper.map(usersDto, Users.class);
        Users createdUser = usersRepository.save(user);

        usersValidator.validate(usersDto,errors);
        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(errors);}

        return ResponseEntity.ok().body(createdUser);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid UsersDto usersDto, Errors errors){

        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(errors);}

        //Users user = modelMapper.map(usersDto, Users.class); - modelMapper 로 하는 거 일단 실패,,,
        Users user = usersRepository.findById(Long.parseLong(id)).get();
        user.setEmail(usersDto.getEmail());
        user.setName(usersDto.getName());

        Users updatedUser = usersRepository.save(user);

        return ResponseEntity.ok().body(updatedUser);
    }


    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){

        usersRepository.deleteById(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }
}
