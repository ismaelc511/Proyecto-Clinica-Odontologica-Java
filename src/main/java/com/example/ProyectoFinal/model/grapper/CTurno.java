package com.example.ProyectoFinal.model.grapper;

import com.example.ProyectoFinal.model.Odontologo;
import com.example.ProyectoFinal.model.Paciente;

import java.util.Date;

public class CTurno {
    private Integer id;

    //Relaciones con otras clases
    private Paciente paciente;
    private Odontologo odontologo;

    private String fecha;

    public CTurno() {
    }

    public CTurno(Integer id, Paciente paciente, Odontologo odontologo, String fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }
    public CTurno(Paciente paciente, Odontologo odontologo, String fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public String getDate() {
        return fecha;
    }

    public void setDate(String fecha) {
        this.fecha = fecha;
    }
}
