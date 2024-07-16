package org.Population.Repository;


import org.Population.Model.Department;
import org.Population.Model.Region;
import org.Population.Model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String> {
    List<Ville> findByCodeDepartement(String codeDepartement);
    List<Ville> findByCodeRegion(String codeRegion);
}



