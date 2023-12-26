package com.neki.neki_skills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Neki Skills Api", version = "1", description = "API desenvolvida para manipulação de skills de usuário"))
public class NekiSkillsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NekiSkillsApplication.class, args);
    }

}
