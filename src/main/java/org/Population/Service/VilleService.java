package org.Population.Service;

import org.Population.Model.Ville;
import org.Population.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public List<Ville> findTop10MostPopulatedDepartments() {
        try {
            return villeRepository.findAll().stream()
                    .collect(Collectors.groupingBy(Ville::getCodeDepartement,
                            Collectors.summingInt(Ville::getPopulation)))
                    .entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                    .limit(10)
                    .map(entry -> new Ville(entry.getKey(), null, null, null, entry.getValue()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ville> findTop10MostPopulatedRegions() {
        try {
            return villeRepository.findAll().stream()
                    .collect(Collectors.groupingBy(Ville::getCodeRegion,
                            Collectors.summingInt(Ville::getPopulation)))
                    .entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                    .limit(10)
                    .map(entry -> new Ville(null, entry.getKey(), null, null, entry.getValue()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ville findMostPopulatedVilleByDepartment(String codeDepartement) {
        try {
            return villeRepository.findByCodeDepartement(codeDepartement).stream()
                    .max(Comparator.comparingInt(Ville::getPopulation))
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ville findMostPopulatedVilleByRegion(String codeRegion) {
        try {
            return villeRepository.findByCodeRegion(codeRegion).stream()
                    .max(Comparator.comparingInt(Ville::getPopulation))
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getPopulationByDepartment(String codeDepartement) {
        try {
            return villeRepository.findByCodeDepartement(codeDepartement).stream()
                    .mapToInt(Ville::getPopulation)
                    .sum();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPopulationByRegion(String codeRegion) {
        try {
            return villeRepository.findByCodeRegion(codeRegion).stream()
                    .mapToInt(Ville::getPopulation)
                    .sum();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPopulationByVille(String codeCommune) {
        try {
            Optional<Ville> ville = villeRepository.findById(codeCommune);
            return ville.map(Ville::getPopulation).orElse(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Ville> findTop10MostPopulatedVilles() {
        try {
            return villeRepository.findAll().stream()
                    .sorted(Comparator.comparingInt(Ville::getPopulation).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ville findMostPopulatedVilleInFrance() {
        try {
            return villeRepository.findAll().stream()
                    .max(Comparator.comparingInt(Ville::getPopulation))
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
