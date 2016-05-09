package com.farmacia.jersey.farmacia.model;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.farmacia.jersey.farmacia.model.Farmacia;
import com.farmacia.jersey.jdbc.DbConnection;
import com.farmacia.jersey.jdbc.DbUtil;

public class FarmaciaDao {

    private Connection connection;
    private Statement statement;
 
    public FarmaciaDao() { }
 
    public List<Farmacia> getFarmacias() throws SQLException {
        String query = "SELECT * FROM farmacia";
        List<Farmacia> list = new ArrayList<Farmacia>();
        Farmacia farmacia = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                farmacia = new Farmacia();
                /*Retrieve one employee details 
                and store it in employee object*/
                farmacia.setId_farmacia(rs.getInt("id_farmacia"));
                farmacia.setDireccion(rs.getString("direccion"));
                farmacia.setTitulo(rs.getString("titulo"));
                farmacia.setDescripcion(rs.getString("descripcion"));

 
                //add each employee to the list
                list.add(farmacia);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }

}
