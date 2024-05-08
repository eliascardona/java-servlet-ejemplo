package dev.elias.jsonapi.demo;

import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PostOneStudent", urlPatterns = "/postOneStudent")

// Esta es nuestra rama de git de pruebas

public class PostOneStudent extends HttpServlet {

    public StudentService studentService;

    public PostOneStudent() {
        this.studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // add student from request body
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int responseCode = HttpServletResponse.SC_OK;

        // perform the action of add data to database, then format a response to the client
        boolean res = this.studentService.createStudent(reqBody);
        String responsePayload;
        //responsePayload = this.studentService.oneStudentToJson(reqBody);

        if(!res) {
            responseCode = HttpServletResponse.SC_BAD_REQUEST;
            responsePayload = "bad request";
        }
        responsePayload = reqBody;
        this.outputResponse(resp, responsePayload, responseCode);

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
