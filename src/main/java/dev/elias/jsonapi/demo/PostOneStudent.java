package dev.elias.jsonapi.demo;

import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dev.elias.jsonapi.demo.entity.Student;
import dev.elias.jsonapi.demo.entity.ResponseInterface;


@WebServlet(name = "PostOneStudent", urlPatterns = "/postOneStudent")



public class PostOneStudent extends HttpServlet {

    private StudentService studentService;
    private ResponseService responseService;

    public PostOneStudent() {
        this.studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // add student from request body
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int responseCode = HttpServletResponse.SC_OK;
        boolean res = this.studentService.createStudent(reqBody);

        String reqField = this.fromJsonToClass(reqBody);


        this.responseService = new ResponseService(reqField);
        String fmtJson = this.responseService.formatResponse();

        if(!res) {
            responseCode = HttpServletResponse.SC_BAD_REQUEST;
        }
        this.outputResponse(resp, fmtJson, responseCode);
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////// private methods ////////////////////////////
    ////////////////////////////////////////////////////////////////////

    private String fromJsonToClass(String jsonPayload) {
        String gradeIdVal;
        Gson gson = new Gson();

        Student studentPaylaod = (Student) gson.fromJson(jsonPayload, Student.class);
        gradeIdVal = studentPaylaod.getGradeId();

        return gradeIdVal;
    }

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
