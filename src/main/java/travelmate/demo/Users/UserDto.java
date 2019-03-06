package travelmate.demo.Users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
public class UserDto {
    @Column(name = "email")
    private String email;
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
    @Column(name = "language")
    private String language;

}
