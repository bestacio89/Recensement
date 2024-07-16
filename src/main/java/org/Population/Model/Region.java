package org.Population.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for ID
    private int id;

    @Column(nullable = false)
    private String name;

    private long population;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL) // Cascade operations
    private List<Department> departments = new ArrayList<>();

    public Region() {
    }

    public Region(int id, String name, long population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
        department.setRegion(this); // Set the bidirectional link
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
        department.setRegion(null); // Remove the bidirectional link
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
