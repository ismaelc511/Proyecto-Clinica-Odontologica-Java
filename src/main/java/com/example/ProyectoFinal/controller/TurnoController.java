package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.exceptions.BadRequestException;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Turno;
import com.example.ProyectoFinal.model.grapper.CTurno;
import com.example.ProyectoFinal.service.ITurnoService;
import com.example.ProyectoFinal.service.TurnoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@CrossOrigin("*")
public class TurnoController {
    //Se inyecta la dependencia
    @Autowired
    private ITurnoService turnoService;


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscar(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.findById(id);
        if(turno != null){
            return ResponseEntity.ok(turno);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() throws Exception {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody String turno) throws BadRequestException {
        Gson gson = new Gson();
        CTurno obj = new CTurno();
        try{
            obj = gson.fromJson(turno, CTurno.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        Turno objTransaccional = new Turno();
        if(obj != null){
            objTransaccional = new Turno(obj);
            turnoService.saveTurno(objTransaccional);
        }
        return ResponseEntity.ok(objTransaccional);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id)throws ResourceNotFoundException {
        turnoService.deleteTurno(id);
        return ResponseEntity.ok().body("Eliminado");
    }

    @PutMapping
    public ResponseEntity<Turno> actulizar(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.updateTurno(turno));
    }

}
