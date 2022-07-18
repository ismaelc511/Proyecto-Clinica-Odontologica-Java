package com.example.ProyectoFinal.serviceTest;

import com.example.ProyectoFinal.model.Domicilio;
import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.model.Paciente;
import com.example.ProyectoFinal.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.assertj.core.api.Assert;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import org.springframework.transaction.annotation.Transactional;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PacienteIntegracionTest {


    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MockMvc mockMvc;


    public void cargarDataSet(){
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Domicilio domicilio1 = new Domicilio("Calle", "222", "Temperley", "Bogotá");
        Paciente p1 = pacienteService.guardar(new Paciente("Micaela", "Pereyra", "12345678", new Date(), domicilio));

    }

    @Test
    public void listarPacientes() throws Exception {
        //Cargar algunos pacientes
        this.cargarDataSet();
        //Debemos llamar a un metodo del controller
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        //Cargar algunos pacientes
        this.cargarDataSet();
        //Debemos llamar a un metodo del controller
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals("application/json", response.getResponse().getContentType());
    }

    /*@Test
    public void guardarOdontologoMock() throws Exception{
        Odontologo odontologo = new Odontologo("123", "Felipe", "Monterrosa");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String odontolgoJson = writer.writeValueAsString(odontologo);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontolgoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertFalse(response.getResponse().getContentAsString().isEmpty());
    }*/





}
