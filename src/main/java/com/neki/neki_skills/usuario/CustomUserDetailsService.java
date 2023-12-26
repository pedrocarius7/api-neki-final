package com.neki.neki_skills.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(nome).get();
        List<String> roles = new ArrayList<>();
        roles.add("USUARIO");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(usuario.getNome())
                        .password(usuario.getSenha())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}