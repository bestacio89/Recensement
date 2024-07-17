package org.Population;

import org.Population.Helpers.CSVParser;
import org.Population.Model.Ville;
import org.Population.Repository.VilleRepository;
import org.Population.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

    @Autowired
    private VilleRepository villeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        populateDatabaseIfEmpty();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. 10 most populated departments");
            System.out.println("2. 10 most populated regions");
            System.out.println("3. Most populated ville in department");
            System.out.println("4. Most populated ville in region");
            System.out.println("5. Population of a department");
            System.out.println("6. Population of a region");
            System.out.println("7. Population of a ville");
            System.out.println("8. 10 most populated villes in France");
            System.out.println("9. Most Populate ville in France");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Ville> topDepartments = villeService.findTop10MostPopulatedDepartments();
                    topDepartments.forEach(v -> System.out.println("Department: " + v.getCodeDepartement() + ", Population: " + v.getPopulation()));
                    break;
                case 2:
                    List<Ville> topRegions = villeService.findTop10MostPopulatedRegions();
                    topRegions.forEach(v -> System.out.println("Region: " + v.getCodeRegion() + ", Population: " + v.getPopulation()));
                    break;
                case 3:
                    System.out.print("Enter department code: ");
                    String depCode = scanner.nextLine();
                    Ville mostPopulatedVilleInDep = villeService.findMostPopulatedVilleByDepartment(depCode);
                    if (mostPopulatedVilleInDep != null) {
                        System.out.println("Most populated ville in department: " + mostPopulatedVilleInDep.getCodeCommune() + ", Population: " + mostPopulatedVilleInDep.getPopulation());
                    } else {
                        System.out.println("No ville found for the department code.");
                    }
                    break;
                case 4:
                    System.out.print("Enter region code: ");
                    String regCode = scanner.nextLine();
                    Ville mostPopulatedVilleInReg = villeService.findMostPopulatedVilleByRegion(regCode);
                    if (mostPopulatedVilleInReg != null) {
                        System.out.println("Most populated ville in region: " + mostPopulatedVilleInReg.getCodeCommune() + ", Population: " + mostPopulatedVilleInReg.getPopulation());
                    } else {
                        System.out.println("No ville found for the region code.");
                    }
                    break;
                case 5:
                    System.out.print("Enter department code: ");
                    depCode = scanner.nextLine();
                    int depPopulation = villeService.getPopulationByDepartment(depCode);
                    System.out.println("Population of department " + depCode + ": " + depPopulation);
                    break;
                case 6:
                    System.out.print("Enter region code: ");
                    regCode = scanner.nextLine();
                    int regPopulation = villeService.getPopulationByRegion(regCode);
                    System.out.println("Population of region " + regCode + ": " + regPopulation);
                    break;
                case 7:
                    System.out.print("Enter commune code: ");
                    String communeCode = scanner.nextLine();
                    int villePopulation = villeService.getPopulationByVille(communeCode);
                    System.out.println("Population of ville " + communeCode + ": " + villePopulation);
                    break;
                case 8:
                    List<Ville> topVilles = villeService.findTop10MostPopulatedVilles();
                    topVilles.forEach(v -> System.out.println("Ville: " + v.getCodeCommune() + ", Population: " + v.getPopulation()));
                    break;
                case 9 :
                    Ville ville = villeService.findMostPopulatedVilleInFrance();
                    String message = String.format("La Ville la plus peuplée en France est: %s population: %d",
                                ville.getNomVille(), ville.getPopulation());
                    System.out.println(message);
                    break;


                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void populateDatabaseIfEmpty() {
        if (villeRepository.count() == 0) {
            try {
                List<Ville> villes = CSVParser.parseCsvFile("path/to/your/csvfile.csv");
                villeRepository.saveAll(villes);
                System.out.println("Database populated with initial data.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
