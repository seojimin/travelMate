package travelmate.demo.users;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

//    private Long matchid;//
}
//////////////////////////////////////////////
// id    email   gender  ... matchid
//  1                           1
//  2                           2
//  3                           1
//  4                           2
//  5                           3
//////////////////////////////////////////////

//travel * user where matchid = 1;

//////////////////////////////////////////////
// matchid  city   country    .......
//  1
//  2
//  3
//  4
//  5
// ////////////////////////////////////////////