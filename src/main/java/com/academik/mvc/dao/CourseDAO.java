/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.dao;

import com.academik.mvc.model.Courses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fidaug
 */
public class CourseDAO implements GeneralDAO<Courses> {

    @Override
    public List<Courses> queryAll() {
        List<Courses> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT code, nombre, descripcion, creditos");
            while (result.next()) {
                Courses c = new Courses();
                c.setCode(result.getLong("code"));
                c.setName(result.getString("nombre"));
                c.setDescription(result.getString("descripcion"));
                c.setCredits(result.getLong("creditos"));
                temp.add(c);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    @Override
    public Courses findById(long id) {
        Courses c = null;
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement("SELECT codigo, nombre,descripcion,creditos");
            stmnt.setLong(1, id);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                c = new Courses();
                c.setCode(result.getLong("codigo"));
                c.setName(result.getString("nombre"));
                c.setDescription(result.getString("descripcion"));
                c.setCredits(result.getLong("creditos"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public void create(Courses element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(long id, Courses element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
