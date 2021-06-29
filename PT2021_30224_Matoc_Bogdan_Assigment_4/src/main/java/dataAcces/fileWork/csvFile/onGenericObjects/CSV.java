package dataAcces.fileWork.csvFile.onGenericObjects;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public abstract class CSV<T> implements RepositoryCSV<T> {
    private final String filePath;
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public CSV(String filePath) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.filePath = filePath;
    }

    @Override
    public List<T> readFromCSVFile() throws FileNotFoundException {
        List<T> beans = new CsvToBeanBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withType(type)
                .build()
                .parse();

        List<T> listWithoutDuplicates = beans.stream().distinct().collect(Collectors.toList());

        return  listWithoutDuplicates;
    }
}
