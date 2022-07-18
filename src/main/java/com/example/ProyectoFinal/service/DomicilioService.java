package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.repository.DomicilioRepository;
import com.example.ProyectoFinal.model.Domicilio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    private DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio d){
        return domicilioRepository.save(d);
    }

    public Domicilio buscar(Integer id){
        Domicilio domicilio = null;
        Optional<Domicilio> optionalDomicilio = domicilioRepository.findById(id);
        if(optionalDomicilio.isPresent()){
            domicilio = optionalDomicilio.get();
        }
        return domicilio;
    }

    public List<Domicilio> buscarTodos() throws Exception {
        return domicilioRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if (this.buscar(id) == null)
            throw new ResourceNotFoundException("No existe un domicilio con el ID: " + id);
        domicilioRepository.deleteById(id);
    }



}
