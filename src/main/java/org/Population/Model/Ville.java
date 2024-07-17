package org.Population.Model;

import javax.persistence.*;

@Entity
@Table(name = "villes")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_region", nullable = false)
    private String nomVille;

    @Column(name = "code_region", nullable = false)
    private String codeRegion;

    @Column(name = "nom_region", nullable = false)
    private String nomRegion;

    @Column(name = "code_departement", nullable = false)
    private String codeDepartement;

    @Column(name = "code_commune", nullable = false)
    private String codeCommune;

    @Column(name = "population", nullable = false)
    private int population;

    // Default constructor
    public Ville() {
    }

    // Parameterized constructor
    public Ville(String codeRegion, String nom, String nomRegion, String codeDepartement, String codeCommune, int population ) {
        this.codeRegion = codeRegion;
        this.nomRegion = nomRegion;
        this.codeDepartement = codeDepartement;
        this.codeCommune = codeCommune;
        this.population = population;
        this.nomVille = nom;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getNomVille() {
        return nomVille;
    }

}