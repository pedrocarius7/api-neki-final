package com.neki.neki_skills.usuario_skill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neki.neki_skills.skill.Skill;
import com.neki.neki_skills.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class UsuarioSkill {

    @Id
    @Column(nullable = false, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, name = "level")
    private Integer level;

    
    
    @Column(name = "skill_id")
    private Long skillId;

    
    
    @Column(name = "usuario_id")
    private Long usuarioId;

}
