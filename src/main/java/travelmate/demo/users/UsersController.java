package travelmate.demo.users;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final UsersValidator usersValidator;

    @Builder //builder pattern 을 만들어주지만 생성자에서만 사용해야함
    public UsersController(UsersRepository usersRepository, ModelMapper modelMapper, UsersValidator usersValidator) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.usersValidator = usersValidator;
    }

    // get all the users
    @GetMapping
    public ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok().body(usersList);
    }

    // get single id
    @GetMapping("/users/{id}")
    public ResponseEntity getId(@PathVariable String id) {
        Users users = usersRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(users);
    }

    // save
    @PostMapping                    //요청온 JSON 을 Java class로 mapping
    public ResponseEntity registration(@RequestBody @Valid UsersDto usersDto, Errors errors) {

        Users user = modelMapper.map(usersDto, Users.class);
        Users createdUser = usersRepository.save(user);

        usersValidator.validate(usersDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok().body(createdUser);
    }

    private ResponseEntity hasPermission(String id, Errors errors, HttpSession session){

        Object tempUser = session.getAttribute("sessionUser");
        if(tempUser == null){ return ResponseEntity.badRequest().body(errors);}
        Users sessionUser = (Users)tempUser;
        if(!id.equals(sessionUser.getId())){ return ResponseEntity.badRequest().body(errors); }

        return ResponseEntity.badRequest().body(errors);
    }

    // update
    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid UsersDto usersDto, Errors errors, HttpSession session) {

        if (errors.hasErrors()) { return ResponseEntity.badRequest().body(errors); }

        hasPermission(id, errors, session);

        //Users user = modelMapper.map(usersDto, users.class); - modelMapper 로 하는 거 일단 실패,,,
        Users user = usersRepository.findById(Long.parseLong(id)).get();
        user.setEmail(usersDto.getEmail());
        user.setName(usersDto.getName());
        user.setAge(usersDto.getAge());
        user.setGender(usersDto.getGender());
        user.setLanguage(usersDto.getLanguage());
        user.setNationality(usersDto.getNationality());

        Users updatedUser = usersRepository.save(user);

        return ResponseEntity.ok().body(updatedUser);
    }

    // delete
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id, Errors errors, HttpSession session) {

        hasPermission(id, errors, session);

        usersRepository.deleteById(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }
}
