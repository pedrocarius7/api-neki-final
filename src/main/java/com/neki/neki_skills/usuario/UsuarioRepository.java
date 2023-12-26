package com.neki.neki_skills.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByNome(String nome);
}
