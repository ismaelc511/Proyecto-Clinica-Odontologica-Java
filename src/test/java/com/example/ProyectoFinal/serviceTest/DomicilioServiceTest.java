package com.example.ProyectoFinal.serviceTest;

import com.example.ProyectoFinal.ProyectoFinalApplication;
import com.example.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal.model.Domicilio;
import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.service.DomicilioService;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DomicilioServiceTest {
    //OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @Autowired
    private DomicilioService domicilioService;

    @Test
    public void agregarYBuscarDomiciliosTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR DOMICILIOS");
        System.out.println("==============================");
        Domicilio domicilio = domicilioService.guardar(new Domicilio("001", "92", "Rodriguez", "Buenos Aires"));

        Assert.assertNotNull(domicilioService.buscar(domicilio.getId()));
        domicilioService.eliminar(domicilio.getId());
    }

    @Test
    public void eliminarDomicilioTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST ELIMINAR DOMICILIO");
        System.out.println("==============================");
        Domicilio domicilio1 = domicilioService.guardar(new Domicilio("003", "94", "Rodriguez", "Buenos Aires"));
        System.out.println(domicilio1);
        System.out.println("El id es: " + domicilio1.getId());
        domicilioService.eliminar(domicilio1.getId());
        Assert.assertTrue(domicilioService.buscar(domicilio1.getId()) == null);
        System.out.println(domicilio1.getId());

    }

    @Test
    public void buscarTodosDomiciliosTest() throws Exception {
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS DOMICILIOS");
        System.out.println("==============================");
        Domicilio domicilio1 = new Domicilio("003", "94", "Rodriguez", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("004", "95", "Rodriguez", "Buenos Aires");
        domicilioService.guardar(domicilio1);
        domicilioService.guardar(domicilio2);

        int tamanio = domicilioService.buscarTodos().size();
        Assert.assertEquals(tamanio, domicilioService.buscarTodos().size());

        domicilioService.eliminar(domicilio1.getId());
        domicilioService.eliminar(domicilio2.getId());
    }
}
