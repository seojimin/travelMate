package travelmate.demo.users;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UsersValidator {

    public void validate(UsersDto usersDto, Errors errors){
        String userPw = usersDto.getPassword();
        if(userPw.length() < 8 || userPw.length() > 16){
            errors.rejectValue("password","passwordLength","too short or too long for user's password");
        }
        if(!userPw.matches(".*?[A-Z].*")||!userPw.matches(".*?[a-z].*")){
            errors.rejectValue("password","passwordLetter","insufficient uppercase or lowercase");
        }
        if(!userPw.matches(".*?[0-9].*")){
            errors.rejectValue("password","passwordNumber","insufficient alphabetical number");
        }

    }
}
