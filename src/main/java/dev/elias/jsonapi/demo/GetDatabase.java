package dev.elias.jsonapi.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.io.OutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// SQL driver
//import com.mysql.cj.jdbc.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import dev.elias.jsonapi.demo.entity.Student;

// servlet declaration
@WebServlet(name = "GetDatabase", urlPatterns = "/getDatabase")


public class GetDatabase extends HttpServlet {
    private String baseDB = System.getenv("SQL_BASE");
    private String user = System.getenv("SQL_USER");
    private String passwordDB = System.getenv("SQL_PASS");
    private ConcurrentMap<Integer, Student> studentMap;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // calling the dbConn
        this.dbConn();

        this.studentMap = new ConcurrentHashMap<>();
        List<Student> list = new ArrayList<>(this.studentMap.values());
        String jsonResp = this.toJson(list);

        this.outputResponse(resp, jsonResp, 200);


    }

    private Connection handleDB() throws SQLException {
        Connection dbconnection = null;
        String connectionString = "" + this.baseDB + "?user=" + this.user + "&password=" + this.passwordDB + "";


        dbconnection = DriverManager.getConnection(connectionString);

        return dbconnection;
    }

    private void dbConn() {
        try {
            Connection dbconnection = this.handleDB();

            Statement dbstatement = dbconnection.createStatement();
            ResultSet rs = dbstatement.executeQuery("SELECT * FROM tablaEstudiante");

            while(rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("uname");
                Integer grade = rs.getInt("grade");
                String gradeId = rs.getString("gradeId");

                Student student = new Student(name, grade, gradeId);
                student.setId(id);
                this.studentMap.put(id, student);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


	////////////////////////////////////////////////////////
	//////////// private methods //////////////////////////
	//////////////////////////////////////////////////////

/*    private Connection handleDB() throws SQLException {
        Connection conn = null;
	    try {
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsDB?" +"user=username&password=password123");
			//
            String connectionString = this.baseDB + "?user=" + this.user + "&password=" + this.passwordDB;
            conn = DriverManager.getConnection(connectionString);
            return conn;

        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }*/

    private String toJson(Object list) {
        if (list == null) {
          return null;
		}
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
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

