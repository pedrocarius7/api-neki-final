package com.neki.neki_skills.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neki.neki_skills.exception.InvalidLoginException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario save(UsuarioForm usuarioForm) {
		validarLogin(usuarioForm.getNome());
		return usuarioRepository.save(mapToEntity(usuarioForm));
	}

	private void validarLogin(String nome) {
		var usuario = usuarioRepository.findByNome(nome);
		if (usuario.isPresent()) {
			throw new InvalidLoginException("Esse login já existe no bando de dados");
		}
	}

	private Usuario mapToEntity(UsuarioForm usuarioForm) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioForm.getNome());
		usuario.setSenha(usuarioForm.getSenha());
		return usuario;
	}
	
	public UsuarioDto mapToDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setNome(usuario.getNome());
		return usuarioDto;
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).isPresent() ? usuarioRepository.findById(id).get() : null;
	}
	
}
