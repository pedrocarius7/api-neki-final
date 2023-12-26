package com.neki.neki_skills.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api/usuario")
@Tag(name = "Usuário", description = "Endpoints")
public class UsuarioController {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioService service;


	@PostMapping("/salvar")
	public ResponseEntity<UsuarioDto> saveUsuario(@Valid @RequestBody UsuarioForm usuarioForm) {
		usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
		Usuario novoUsuario = service.save(usuarioForm);
		return new ResponseEntity<>(service.mapToDto(novoUsuario), HttpStatus.CREATED);
	}
	
	
}
