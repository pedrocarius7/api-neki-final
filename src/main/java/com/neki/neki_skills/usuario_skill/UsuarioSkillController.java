package com.neki.neki_skills.usuario_skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.neki_skills.exception.NoSuchElementFoundException;
import com.neki.neki_skills.usuario.Usuario;
import com.neki.neki_skills.usuario.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api/usuario_skill")
@Tag(name = "UsuarioSkill", description = "Endpoints")
public class UsuarioSkillController {

	@Autowired
	private UsuarioSkillService usuarioSkillService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Operation(operationId = "saveUsuarioSkill", summary = "Salvar UsuarioSkill", tags = {
	"UsuarioSkill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/salvar")
	public ResponseEntity<UsuarioSkillDto> saveUsuarioSkill(@Valid @RequestBody UsuarioSkillDto usuarioSkillDto) {
		UsuarioSkill novoUsuarioSkill = usuarioSkillService.save(usuarioSkillDto);
		return new ResponseEntity<>(usuarioSkillService.mapToDto(novoUsuarioSkill), HttpStatus.CREATED);
	} 
	
	@Operation(operationId = "listSkillUsuario", summary = "Listar UsuarioSkill por id", tags = {
	"UsuarioSkill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/listarSkillUsuario/{id}")
	public ResponseEntity<List<UsuarioSkillListDto>> listSkillUsuario(@PathVariable Long id){
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			throw new NoSuchElementFoundException("Não existe nenhum UsuarioSkill com o ID: " + id + ".");
		}else {
			return new ResponseEntity<>(usuarioSkillService.getUsuarioSkill(id), HttpStatus.OK);
		}
	}
	
	@Operation(operationId = "updateUsuarioSkill", summary = "Atualizar UsuarioSkill", tags = {
	"UsuarioSkill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioSkillDto> updateUsuarioSkill(@Valid @RequestBody UsuarioSkillDto usuarioSkillDto){
		
		UsuarioSkill novoUsuarioSkill = usuarioSkillService.update(usuarioSkillDto);
		return new ResponseEntity<>(usuarioSkillService.mapToDto(novoUsuarioSkill), HttpStatus.CREATED);
		
	}
	
	@Operation(operationId = "deleteUsuarioSkill", summary = "Excluir UsuarioSkill por id", tags = {
	"UsuarioSkill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuarioSkill(@PathVariable Long id){
		
		if(usuarioSkillService.findById(id) == null) {
			throw new NoSuchElementFoundException("O UsuarioSkill com o ID: " + id + " não existe.");
		
		}else {
		
			usuarioSkillService.deleteById(id);
			return new ResponseEntity<>("UsuarioSkill deletada com sucesso.", HttpStatus.OK);
		}
	
	}
	
}
