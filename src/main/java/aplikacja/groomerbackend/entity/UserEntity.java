package aplikacja.groomerbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isActivated;


    public UserEntity(String username,String email,String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserEntity(String username,String email,String password,String avatar,Role role,boolean isActivated){
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
        this.isActivated = isActivated;
    }

}
