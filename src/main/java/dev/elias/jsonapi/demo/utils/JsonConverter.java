package dev.elias.jsonapi.demo.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JsonConverter {
    private ResultSet resultSet;

    public JsonConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String convertResultSetToJson() {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        while (resultSet.next()) {
            jsonBuilder.append("{");

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnValue = resultSet.getString(i);
                jsonBuilder.append("\"").append(columnName).append("\":\"").append(columnValue).append("\"");
                if (i < columnCount) {
                    jsonBuilder.append(",");
                }
            }

            jsonBuilder.append("}");
            if (!resultSet.isLast()) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}



