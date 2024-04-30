package dev.elias.jsonapi.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


import com.google.gson.Gson;
import dev.elias.jsonapi.demo.entity.Student;


public class StudentService {
    private AtomicInteger key;
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

    /**
     * Create student from the json payload
     *
     * @param jsonPayload
     * @return
     */
    public boolean createStudent(String jsonPayload) {
        if (jsonPayload == null) {
          return false;
		}

        Gson gson = new Gson();
        try {
            Student student = (Student) gson.fromJson(jsonPayload, Student.class);
            if (student != null) {
                return this.addStudent(student);
            }
        }
        catch (Exception e) {}
        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////// inner methods ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

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

    private boolean addStudent(Student student) {
        Integer id = key.incrementAndGet();
        student.setId(id);
        this.students.put(id, student);
        return true;
    }
}






