package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.exceptions.BadRequestException;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Turno;
import com.example.ProyectoFinal.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    //Se inyecta la dependencia
    @Autowired
    private TurnoService turnoService;

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Integer id){
        Turno turno = turnoService.buscar(id);
        if(turno != null){
            return ResponseEntity.ok(turno);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() throws Exception {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) throws BadRequestException {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id)throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok().body("Eliminado");
    }

    @PutMapping
    public ResponseEntity<Turno> actulizar(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

}
