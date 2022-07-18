package com.example.ProyectoFinal.serviceTest;

import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Domicilio;
import com.example.ProyectoFinal.model.Paciente;
import com.example.ProyectoFinal.service.DomicilioService;
import com.example.ProyectoFinal.service.PacienteService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PacienteServiceTest {

    //private static PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DomicilioService domicilioService;

    //private DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());


    @Test
    public void agregarYBuscarPacienteTest(){
        //this.cargarDataSet();
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR PACIENTE");
        System.out.println("==============================");
        Domicilio domicilio = domicilioService.guardar(new Domicilio("Calle", "123", "Temperley", "Buenos Aires"));
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
        //pacienteService.eliminar(p.getId());
        //domicilioService.eliminar(domicilio.getId());
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        pacienteService.guardar(p);
        System.out.println(p);
        pacienteService.eliminar(p.getId());
        Assert.assertTrue(pacienteService.buscar(p.getId()) == null);
    }

    @Test
    public void traerTodos() throws Exception {
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS PACIENTES");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));

        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);


    }

}
