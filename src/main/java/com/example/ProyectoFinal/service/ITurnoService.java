package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.exceptions.BadRequestException;
import com.example.ProyectoFinal.model.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    List<Turno> listarTurnos();
    Turno saveTurno(Turno turno)throws BadRequestException;
    Optional<Turno> findById(int id);
    void deleteTurno(int id);
    Turno updateTurno(Turno turno);
}
