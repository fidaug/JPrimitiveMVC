package com.academik.mvc.controller;


import com.academik.mvc.dao.CourseDAO;
import com.academik.mvc.model.Courses;
import java.io.IOException;
import static java.time.Clock.system;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fidaug
 */
@WebServlet("/courses/*")
public class CoursesController extends HttpServlet {
    
    CourseDAO dao = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if (complement == null) {
            complement = "";
            System.err.println(complement);
            String redirectPage;
            switch(complement){
                case "/create":
                    redirectPage = "Course-create.jsp";
                    break;
                case "/view":
                    long idToView = Long.parseLong(req.getParameter("id"));
                    Courses sToView = dao.findById(idToView);
                    req.setAttribute("single_course", sToView);
                    redirectPage = "Course-view.jsp";
                    break;
                case "/edit":
                    System.err.println("NO IMPLEMENTADO");
                    break;
                case "/list":
                case "/":
                case "":
                    List<Courses> allCourses = dao.queryAll();
                    req.setAttribute("list_of_courses",allCourses);
                    redirectPage = "course-list.jpg";
                    break;
                default:
                    resp.sendRedirect(req.getContextPath()+ "/courses");
                    return;
            }
        }
    }
    
    
}
