package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.exceptions.BadRequestException;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.repository.TurnoRepository;
import com.example.ProyectoFinal.model.Turno;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    //private IDao<Turno> turnoIDao;
    private TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardar(Turno turno) throws BadRequestException {
        turno.setFecha(new Date());
        return turnoRepository.save(turno);
    }

    public void eliminar(Integer id) throws ResourceNotFoundException{
        //turnoRepository.deleteById(id);

        if (this.buscar(id) == null)
            throw new ResourceNotFoundException("No existe un turno con el ID: " + id);
        turnoRepository.deleteById(id);
    }

    public Turno buscar(Integer id) {
        Turno turno = null;
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if(optionalTurno.isPresent()){
            turno= optionalTurno.get();
        }
        return turno;
    }

    public List<Turno> buscarTodos() throws Exception {
        return turnoRepository.findAll();
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }


}
