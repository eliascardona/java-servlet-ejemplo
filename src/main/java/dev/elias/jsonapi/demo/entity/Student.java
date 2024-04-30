package dev.elias.jsonapi.demo.entity;

public class Student {
    private Integer id;
    private String name;
    private Integer grade;
    private String gradeId;

    public Student() {}

    public Student(String name, Integer grade, String gradeId) {
        this.name = name;
        this.grade = grade;
        this.gradeId = gradeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
}
