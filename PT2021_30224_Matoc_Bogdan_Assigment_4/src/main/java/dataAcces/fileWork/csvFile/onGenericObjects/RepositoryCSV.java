package dataAcces.fileWork.csvFile.onGenericObjects;

import java.io.FileNotFoundException;
import java.util.List;

public interface RepositoryCSV<T> {
    List<T> readFromCSVFile() throws FileNotFoundException;
}
