package com.example.ProyectoFinal.repository;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

     T guardar(T t);
     T buscar(Integer id);
     void eliminar(Integer id);
     List<T> buscarTodos() throws ClassNotFoundException, SQLException, Exception;
     T actualizar(T t);


}
