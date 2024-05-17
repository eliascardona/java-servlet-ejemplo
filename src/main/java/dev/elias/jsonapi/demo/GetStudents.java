package dev.elias.jsonapi.demo;

import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "GetStudents", urlPatterns = "/getStudents")


public class GetStudents extends HttpServlet {

    public StudentService studentService;

    public GetStudents() {
        this.studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // testig the get methods before implementation
        String jsonPayload = this.studentService.findAllStudents();
        this.outputResponse(resp, jsonPayload, 200);
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////// private methods ////////////////////////////
    ////////////////////////////////////////////////////////////////////

    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if(payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}


