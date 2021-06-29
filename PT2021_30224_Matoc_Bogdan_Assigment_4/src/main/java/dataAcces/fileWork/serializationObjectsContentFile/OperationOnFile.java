package dataAcces.fileWork.serializationObjectsContentFile;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationOnFile<T extends Serializable> {
    String filename;
    Class<T> type;

    @SuppressWarnings("unchecked")
    public OperationOnFile(Class<T> type, String filename) {
        this.type = type;
        this.filename = filename;
    }

    public List<T> read() throws IOException, ClassNotFoundException{
        List<T> objects = new ArrayList<>();
        FileInputStream file = new FileInputStream(filename);
        if(file.available() != 0){ //file is empty
            ObjectInputStream in = new ObjectInputStream(file);
            Object fileContent = in.readObject();
            if(fileContent != null){
                objects = (List<T>) fileContent;
            }
            in.close();
        }
        file.close();

        return objects;
    }

    public void write(T object) throws IOException {
        FileOutputStream file = new FileOutputStream (filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(object);
        out.close();
        file.close();
    }

    public void write(List<T> object) throws IOException {
        System.out.println(object);
        FileOutputStream file = new FileOutputStream (filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(object);
        out.close();
        file.close();
    }

}
