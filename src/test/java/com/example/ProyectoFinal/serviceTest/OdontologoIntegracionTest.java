package com.example.ProyectoFinal.serviceTest;


import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.service.OdontologoService;
import com.example.ProyectoFinal.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void registrarOdontologo() throws Exception {
        //Crea un nuevo objeto Odontologo
        Odontologo odontologo = new Odontologo(1, "345", "Juan", "Rosales");
        //Ahora se debe convertir el objeto a un String Json para enviarlo al REQUEST
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String odontolgoJson = writer.writeValueAsString(odontologo);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontolgoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void actualizarOdontologoMock() throws Exception{

        Odontologo odontologoCreado = odontologoService.guardar(new Odontologo(2,"ABC123","Andres","Monterrosa"));
        assertEquals("Andres", odontologoCreado.getNombre());
        odontologoCreado.setNombre("Felipe");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String odontolgoJson = writer.writeValueAsString(odontologoCreado);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/{id}", odontologoCreado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontolgoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertTrue(response.getResponse().getContentAsString().contains("\"nombre\":\"Felipe\""));
    }

}
