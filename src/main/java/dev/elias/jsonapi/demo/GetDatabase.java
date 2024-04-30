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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


import dev.elias.jsonapi.demo.utils.JsonConverter;


@WebServlet(name = "GetDatabase", urlPatterns = "/getDatabase")


public class GetDatabase extends HttpServlet {

    private String url = System.getenv("SQL_BASE");
    private String user = System.getenv("SQL_USER");
    private String password = System.getenv("SQL_PASS");
	
    private String refer;
    private Connection dbconnection;
    private JsonConverter converter;

    public GetDatabase() {
        this.refer = "Just a reference string applied to the constructor";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.dbconnection = DriverManager.getConnection(url, user, password);
        String dbquery = "SELECT * FROM tabla_con_codigo";

        Statement statement = this.dbconnection.createStatement();

        String jsonPayload = this.applyingConverter(statement, dbquery);
        this.outputResponse(resp, jsonPayload, 200);

        this.dbconnection.close();
    }

    //////////////////////////////////////////////////////////////////////////////
    /////////////////////// private methods /////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    private String applyingConverter(Statement dbstatement, String query) {
        try {
            ResultSet rSQL = dbstatement.executeQuery(query);

			// Create an instance of JsonConverter
            this.converter = new JsonConverter(rSQL);
            // End of instance

            String jsonResponse = this.converter.convertResultSetToJson();

            rSQL.close();
            dbstatement.close();

            return jsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
		}

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