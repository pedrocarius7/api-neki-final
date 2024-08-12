package com.neki.neki_skills.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neki.neki_skills.auth.JwtUtil;
import com.neki.neki_skills.config.security.ErrorRes;
import com.neki.neki_skills.config.security.LoginReq;
import com.neki.neki_skills.config.security.LoginRes;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest/auth/")
@Slf4j
public class AuthenticationController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {
    	
    	log.info("Inicio do login");
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getNome(), loginReq.getSenha()));
            String nome = authentication.getName();
            Usuario usuario = new Usuario(nome,"");
            String token = jwtUtil.createToken(usuario);
            LoginRes loginRes = new LoginRes(nome,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
        	log.error("Deu ruim");
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid usuarioname or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}