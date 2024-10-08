/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import model.StudentTable;
import java.util.List;
/**
 *
 * @author Admin
 */
public class GreetingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gpa = request.getParameter("gpa");
        
        Student student = new Student(id);
        student.setName(name);
        student.setGpa(gpa);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GreetingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Hello " + id + " " + name + " " + gpa +"</h1>");
            if(StudentTable.findStudentById(id) != null){
                out.println("<h1>There is an existing student with this ID</h1>");
            }else{
                StudentTable.insertStudent(student);
                out.println("<h1>Id:"+ id + "</h1>");
                out.println("<br>");
                out.println("<h1>Name:"+ name + "</h1>");
                out.println("<br>");
                out.println("<h1>GPA:"+ gpa + "</h1>");
                out.println("<br>");
                out.println("<h1>is added</h1>");
            }
            out.println("<br>");
            List<Student> studentList = StudentTable.findAllStudent();
            out.println("<h1>Student List</h1>");
            out.println("<br>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>GPA</th></tr>");
            for(Student eachStudent: studentList){
                out.println("<tr>");
                out.println("<td>"+ eachStudent.getId() + "</td>");
                out.println("<td>"+ eachStudent.getName() + "</td>");
                out.println("<td>"+ eachStudent.getGpa() + "</td>");
                out.println("</tr>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
