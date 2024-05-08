package dev.elias.jsonapi.demo.services;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;

import dev.elias.jsonapi.demo.entity.Student;


public class StudentJsonDeserializer implements JsonDeserializer<Student> {
	private String auxRefer;

    public StudentJsonDeserializer() {
        this.auxRefer = "refer";
	}


    //////////////////////////////////////////////////////////// 
    //////////////////// functional methods ////////////////////
    ////////////////////////////////////////////////////////////

    @Override
    public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
        throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            /*
                js = {
                  uname: evt.target.uname.value,
                  grade: parseInt(evt.target.grade.value),
                  gradeId: evt.target.gradeId.value
                }
            */

            String name = jsonObject.get("uname").getAsString();
            Integer gradeInt = jsonObject.get("grade").getAsInt();
            String gradeId = jsonObject.get("gradeId").getAsString();

            Student studentTemp = new Student(name, gradeInt, gradeId);


            return studentTemp;
        }
}

















