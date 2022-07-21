package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Paciente;
import com.example.ProyectoFinal.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    //Se inyecta la dependencia
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscar(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() throws Exception {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.ok().body("Eliminado");
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping
    public ResponseEntity<Paciente> actulizar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }

}
