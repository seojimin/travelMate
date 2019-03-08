package travelmate.demo.Users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UsersDto {
    @NotEmpty
    @Column(name = "email")
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;
    @NotEmpty
    @Column(name = "language")
    private String language;

}
