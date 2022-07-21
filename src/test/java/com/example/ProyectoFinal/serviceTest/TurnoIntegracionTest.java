package com.example.ProyectoFinal.serviceTest;


import com.example.ProyectoFinal.model.Domicilio;
import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.model.Paciente;
import com.example.ProyectoFinal.model.Turno;
import com.example.ProyectoFinal.service.OdontologoService;
import com.example.ProyectoFinal.service.PacienteService;
import com.example.ProyectoFinal.service.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoIntegracionTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private MockMvc mockMvc;


    public void cargarDataSet() throws Exception {
        //Se registrar Paciente y Odontologo
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        Odontologo o = odontologoService.guardar(new Odontologo("3456", "Carlos", "Rosales"));
        //Se registra un Turno
        Turno turno= new Turno();
        turno.setPaciente(pacienteService.buscar(1));
        turno.setOdontologo(odontologoService.buscar(1));
        turno.setFecha(new Date());
        turnoService.guardar(turno);
    }

    @Test
    public void listarTurnos() throws Exception{
        //Carga un turno
        this.cargarDataSet();
        //Se debe llamar al endpoint correspondiente del controller usando MockMvc
        MvcResult response= this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

}
