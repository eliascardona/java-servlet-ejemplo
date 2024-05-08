package dev.elias.jsonapi.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;

import dev.elias.jsonapi.demo.entity.Student;
//import dev.elias.jsonapi.demo.services.StudentJsonDeserializer;


public class StudentService {
    private Integer key;
    private ConcurrentMap<Integer, Student> students;

    public StudentService() {
        this.students = new ConcurrentHashMap<>();
        key = new AtomicInteger();

        this.addStudent(new Student("Elias", 10, "A"));
        this.addStudent(new Student("Jesus", 10, "B"));
        this.addStudent(new Student("Fabian", 10, "C"));
        this.addStudent(new Student("Caleb", 10, "D"));
        this.addStudent(new Student("Neto", 10, "E"));
        this.addStudent(new Student("Andre", 10, "F"));
    }

    public String findAllStudents() {
        List<Student> list = new ArrayList<>(this.students.values());
        // convert the list to json and return as string
        return this.toJson(list);
    }

    public boolean createStudent(String jsonPayload) {
        if (jsonPayload == null) {
          return false;
		}

        Gson gson = new Gson();
        try {
            Student oneStudent = (Student) gson.fromJson(jsonPayload, Student.class);
            if (oneStudent != null) {
                return this.addStudent(oneStudent);
            }
        }
        catch (Exception e) {}
        return false;
    }


    /*public String oneStudentToJson(String jsonPayload) {
        if (jsonPayload == null) {
          return null;
		}

        Student oneStudent;
        Gson gson = new Gson();
        try {
            oneStudent = (Student) gson.fromJson(jsonPayload, Student.class);
            if (oneStudent != null) {
                JsonDeserializer<Student.class> jsonFieldsHandlerClass = new StudentJsonDeserializer();
                oneStudent = jsonFieldsHandlerClass.getFields();

                return this.createOneStudent(oneStudent);
            }
        }
        catch (Exception e) {}
        return null;

        // convert one student object to json and return as string
        return this.toJson(oneStudent);
    }*/


    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////// inner methods ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private String toJson(Object objectTemp) {
        if (objectTemp == null) {
          return null;
		}
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(objectTemp);
        }
        catch (Exception e) {}
        return json;
    }

    private boolean addStudent(Student student) {
        Integer id = key.incrementAndGet();
        student.setId(id); 
        this.students.put(student);
        return true;
    }

    private Student createOneStudent(Student student) {
        return student;
    }

}














