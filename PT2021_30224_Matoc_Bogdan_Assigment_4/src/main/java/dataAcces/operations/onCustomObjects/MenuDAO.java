package dataAcces.operations.onCustomObjects;


import dataAcces.fileWork.serializationObjectsContentFile.OperationOnFile;
import dataAcces.operations.onGenericObjects.DAO;
import model.entities.menu.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends DAO<Menu> {
    public MenuDAO() {
        super("src/main/resources/menu.txt");
    }
}
