package by.tc.task.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Y50-70 on 24.01.2018.
 */
public class Person implements Serializable{
    private static final long serialVersionUID = 51938856458277868L;
    private String name;
    private String surname;
    private String nameEn;
    private String surnameEn;
    private String role;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public void setSurnameEn(String surnameEn) {
        this.surnameEn = surnameEn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(nameEn, person.nameEn) &&
                Objects.equals(surnameEn, person.surnameEn) &&
                Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, nameEn, surnameEn, role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", surnameEn='" + surnameEn + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
