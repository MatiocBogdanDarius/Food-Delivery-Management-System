package dataAcces.operations.onGenericObjects;

import dataAcces.fileWork.serializationObjectsContentFile.OperationOnFile;
import model.entities.users.User;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


/**
 * this interface contains the header of the methods for interacting with the database
 */
public abstract class DAO<T extends Serializable> implements RepositoryDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(DAO.class.getName());

    private final Class<T> type;
    private final OperationOnFile<T> operationOnFile;

    @SuppressWarnings("unchecked")
    public DAO(String filePath) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        operationOnFile = new OperationOnFile(type, filePath);
    }

    @Override
    public List<T> findAll() {
        try {
            return operationOnFile.read();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> findByField(String fieldName, String fieldValue) {
        List<T> allObjects = findAll();
        List<T> result = new ArrayList<>();
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
            Method method = propertyDescriptor.getReadMethod();
            allObjects.forEach(object -> {
                try {
                    if (method.invoke(object).toString().equals(fieldValue)) {
                        result.add(object);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void add(T object) {
        try {
            List<T> fileContent = findAll();
            System.out.println(fileContent.add(object));
            operationOnFile.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update (T object) {
        try {
            List<T> fileContent = findAll();
            fileContent.add(object);
            operationOnFile.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) {
        try {
            List<T> fileContent =  findAll();
            fileContent.removeIf(object::equals);
            operationOnFile.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
