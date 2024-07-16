package org.Population.Model;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for ID
    private int id;

    @Column(nullable = false)
    private String name;

    private long population;

    @ManyToOne // Many-to-one relationship with Region
    @JoinColumn(name = "region_id") // Foreign key
    private Region region;

    public Department() {
    }

    public Department(int id, String name, long population) {
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", region=" + (region != null ? region.getName() : null) +
                '}';
    }
}
