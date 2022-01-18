package br.com.luizlmc.DashboardFinanceiro.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
public class SystemUser extends User {

    private static final long serialVersionUID = 1L;

    private br.com.luizlmc.DashboardFinanceiro.model.User user;

    public SystemUser(br.com.luizlmc.DashboardFinanceiro.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}