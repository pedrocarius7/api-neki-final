package com.neki.neki_skills.usuario_skill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long> {
	
	public Optional<UsuarioSkill> findByUsuarioId(Long usuarioId);
	
	@Query("SELECT s.nome as nome , us.level as level\n"
			+ "	FROM UsuarioSkill us\n"
			+ "	INNER JOIN Skill s on s.id = us.skillId\n"
			+ "	WHERE us.usuarioId = :usuarioId")
	public List<UsuarioSkillListDto> getUsuarioSkill(@Param("usuarioId") Long usuarioId);
	
}
