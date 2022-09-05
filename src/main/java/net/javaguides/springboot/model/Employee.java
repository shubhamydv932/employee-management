package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "team")
    private String team;

    public Employee() {
        super();
    }

    public Employee(long empid, String name, String email, String team) {
        super();
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTeam() {
        return team;
    }

    public long getEmpid() {
        return empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

}