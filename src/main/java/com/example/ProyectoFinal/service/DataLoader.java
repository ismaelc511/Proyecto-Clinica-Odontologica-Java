package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.model.Usuario;
import com.example.ProyectoFinal.model.UsuarioRol;
import com.example.ProyectoFinal.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/producto/**")
                        .allowedOrigins("http://localhost:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
            };
        }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Encriptar
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String password1 = passwordEncoder1.encode("hola1");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String password2 = passwordEncoder2.encode("hola2");
        BCryptPasswordEncoder passwordEncoder3 = new BCryptPasswordEncoder();
        String password3 = passwordEncoder3.encode("hola3");

        //Crear usuarios
        Usuario usuario1 = new Usuario("Admin", "admin", "admin@gmail.com", password1, UsuarioRol.ADMIN);
        Usuario usuario2 = new Usuario( "User", "user", "user@gmail.com", password2, UsuarioRol.USER);
        Usuario usuario3 = new Usuario( "User2", "user2", "user2@gmail.com", password3, UsuarioRol.USER);
        this.usuarioRepository.save(usuario1);
        this.usuarioRepository.save(usuario2);
        this.usuarioRepository.save(usuario3);
    }
    }
