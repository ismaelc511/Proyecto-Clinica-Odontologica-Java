package com.example.ProyectoFinal.service;


import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.repository.PacienteRepository;
import com.example.ProyectoFinal.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    //private IDao<Paciente> pacienteIDao;
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente p) {
        p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public Paciente buscar(Integer id) {
        Paciente paciente=null;
        Optional<Paciente> optionalPaciente= pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()){
            paciente= optionalPaciente.get();
        }
        return paciente;
    }

    public List<Paciente> buscarTodos() throws Exception {
        //return pacienteIDao.buscarTodos();
        return pacienteRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        //pacienteRepository.deleteById(id);
        if (this.buscar(id) == null)
            throw new ResourceNotFoundException("No existe un paciente con el Id: " + id);
        pacienteRepository.deleteById(id);
    }

    public Paciente actualizar(Paciente p) {
        //return pacienteIDao.actualizar(p);
        return pacienteRepository.save(p);
    }

}
