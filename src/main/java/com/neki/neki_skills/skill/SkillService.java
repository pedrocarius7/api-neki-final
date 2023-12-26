package com.neki.neki_skills.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.validation.Valid;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	public Skill save(@Valid SkillDto skillDto) {
		return skillRepository.save(mapToEntity(skillDto));
	}

	private Skill mapToEntity(SkillDto skillDto) {
		Skill skill = new Skill();
		skill.setNome(skillDto.getNome());
		skill.setDescricao(skillDto.getDescricao());
		skill.setImgUrl(skillDto.getImgUrl());
		return skill;
	}
}
