package com.neki.neki_skills.usuario_skill;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioSkillPutDto {
	
	@NotNull
	private Long usuarioSkillId;
	
	private Integer level;

}
