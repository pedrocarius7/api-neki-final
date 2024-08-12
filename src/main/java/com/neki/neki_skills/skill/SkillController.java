package com.neki.neki_skills.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api/skill")
@Tag(name = "Skill", description = "Endpoints")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Skill> saveSkill(@Valid @RequestBody SkillDto skillDto){
	Skill novaSkill = skillService.save(skillDto);
	return new ResponseEntity<>(novaSkill, HttpStatus.CREATED);
	}
	
	//@GetMapping("/listaSkill")
	//public ResponseEntity<List<SkillDto>> findSkill
}
