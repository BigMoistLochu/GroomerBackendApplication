package aplikacja.groomerbackend.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    CUSTOMER,EMPLOYEE,ADMIN;


//    public List<SimpleGrantedAuthority> getAuthorities(Role role){
//        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
//        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
//        return simpleGrantedAuthorityList;
//    }
}
