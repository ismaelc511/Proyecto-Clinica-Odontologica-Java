package com.example.ProyectoFinal.serviceTest;

import com.example.ProyectoFinal.controller.OdontologoController;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OdontologoServiceTest {
    //OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoController.class));

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void agregarYBuscarOdontologosTest() throws ResourceNotFoundException {
        logger.info("Iniciamos la prueba de agregar y buscar");
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));

        Assert.assertNotNull(odontologoService.buscar(odontologo.getId()));
        odontologoService.eliminar(odontologo.getId());
    }

    @Test
    @Transactional
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        logger.info("Se va a eliminar el Odontologo");
        System.out.println("==============================");
        System.out.println("TEST ELIMINAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("002", "Martin", "Rodriguez"));
        odontologoService.eliminar(odontologo.getId());
        Assert.assertTrue(odontologoService.buscar(odontologo.getId()) == null);
        logger.info("Se eliminó el odontologo exitosamente");

    }

    @Test
    public void buscarTodosOdontologoTest() throws Exception {
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS ODONTOLOGOS");
        System.out.println("==============================");
        Odontologo odontologo1 = new Odontologo("003", "Nicolas", "Avigliano");
        Odontologo odontologo2 = new Odontologo("004", "Violeta", "Matorras");

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);

        int tamanio = odontologoService.buscarTodos().size();
        Assert.assertEquals(tamanio, odontologoService.buscarTodos().size());

        odontologoService.eliminar(odontologo1.getId());
        odontologoService.eliminar(odontologo2.getId());
    }

    @Test
    public void actualizar(){
        Odontologo odontologo = new Odontologo("4444", "Mario", "Perez");
        odontologoService.guardar(new Odontologo("555", "Tomas", "Pereira"));
        Odontologo odontologo1 = new Odontologo("333", "Pedro", "Martinez");
        Odontologo o1 = odontologoService.guardar(new Odontologo("888", "María", "Hernandez"));
    }

}
