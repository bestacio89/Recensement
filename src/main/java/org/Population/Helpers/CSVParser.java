package org.Population.Helpers;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.Population.Model.Ville;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVParser {
    public static List<Ville> parseCsvFile(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            CsvToBean<Ville> csvToBean = new CsvToBeanBuilder<Ville>(reader)
                    .withType(Ville.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
