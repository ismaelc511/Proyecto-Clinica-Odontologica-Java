package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.exceptions.BadRequestException;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Paciente;
import com.example.ProyectoFinal.repository.OdontologoRepository;
import com.example.ProyectoFinal.repository.PacienteRepository;
import com.example.ProyectoFinal.repository.TurnoRepository;
import com.example.ProyectoFinal.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    //private IDao<Turno> turnoIDao;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;


    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public Turno saveAppointment(Turno turno) throws BadRequestException, IllegalArgumentException{
        if (isTurnoDataCorrect(turno)) {
            return turnoRepository.save(turno);
        } else {
            throw new BadRequestException("Patient or dentist data are non-existent.");
        }
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

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }




    @Override
    public Turno saveTurno(Turno turno) throws BadRequestException, IllegalArgumentException{

        if (isTurnoDataCorrect(turno)) {
            return turnoRepository.save(turno);
        } else {
            throw new BadRequestException("Patient or dentist data are non-existent.");
        }

    }

    @Override
    public Optional<Turno> findById(int id) {
        return turnoRepository.findById(id);
    }

    @Override
    public void deleteTurno(int id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Turno updateTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    public boolean isTurnoDataCorrect(Turno turno){
        boolean respPatient = false;
        boolean respDentist = true;
        if(turno != null && turno.getDate().isAfter(LocalDate.now())){
            if(pacienteRepository.findById(turno.getPaciente().getId()).isPresent()){
                respPatient = true;
            }
            if(odontologoRepository.findById(turno.getOdontologo().getId()).isPresent()){
                respDentist = true;
            }
        }
        return respPatient && respDentist;
    }

}
