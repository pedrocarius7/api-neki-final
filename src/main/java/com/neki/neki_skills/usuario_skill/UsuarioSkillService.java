package com.neki.neki_skills.usuario_skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class UsuarioSkillService {
	
	@Autowired
	private UsuarioSkillRepository usuarioSkillRepository;
	
	public UsuarioSkill save(@Valid UsuarioSkillDto usuarioSkillDto) {
		return usuarioSkillRepository.save(mapToEntity(usuarioSkillDto));
	}

	private UsuarioSkill mapToEntity(UsuarioSkillDto usuarioSkillDto) {
		UsuarioSkill usuarioSkill = new UsuarioSkill();
		usuarioSkill.setUsuarioId(usuarioSkillDto.getUsuarioId());
		usuarioSkill.setSkillId(usuarioSkillDto.getSkillId());
		usuarioSkill.setLevel(usuarioSkillDto.getLevel());
		return usuarioSkill;
	}
	
	public UsuarioSkillDto mapToDto(UsuarioSkill usuarioSkill) {
		UsuarioSkillDto usuarioSkillDto = new UsuarioSkillDto();
		usuarioSkillDto.setUsuarioId(usuarioSkill.getUsuarioId());
		usuarioSkillDto.setSkillId(usuarioSkill.getSkillId());
		usuarioSkillDto.setLevel(usuarioSkill.getLevel());
		return usuarioSkillDto;
	}

	public UsuarioSkill findByUsuarioId(Long id) {
		return usuarioSkillRepository.findByUsuarioId(id).isPresent() ? usuarioSkillRepository.findByUsuarioId(id).get() : null;
	}
	
	public List<UsuarioSkillListDto> getUsuarioSkill(Long usuarioId){
		return usuarioSkillRepository.getUsuarioSkill(usuarioId);
	}

	public UsuarioSkill update(@Valid UsuarioSkillDto usuarioSkillDto) {
		return usuarioSkillRepository.save(mapToEntity(usuarioSkillDto));
	}
	
	public UsuarioSkill findById(Long id) {
		return usuarioSkillRepository.findById(id).isPresent() ? usuarioSkillRepository.findById(id).get() : null;
	}

	public void deleteById(Long id) {
		 usuarioSkillRepository.deleteById(id);
	}

}
