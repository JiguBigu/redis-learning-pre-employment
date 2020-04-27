package jigubigu.com.github.learning.entity;

import java.io.Serializable;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 12:38
 */
public class Student implements Serializable {
    private Integer id;

    private String name;

    private Character sex;

    public Student() {
    }

    public Student(Integer id, String name, Character sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
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

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
