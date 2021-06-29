package presentationLayer.controller.tableInitialization.genric;

import businessLogic.BusinessLogic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;
import model.dto.DTO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class InitializationTable<T extends DTO, U extends BusinessLogic> {
    private Class<T> type;
    private U logicObject;
    private TableView<T> table;

    @SuppressWarnings("unchecked")
    public InitializationTable(U logicObject, TableView<T> table) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.logicObject = logicObject;
        this.table = table;
    }

    public void setTableWithEditableCell(){
        setTableWithNonEditableCell();
        for(TableColumn<T, ?> tableColumn:table.getColumns()){
            tableColumn.setOnEditCommit(this::editCellEvenHandle);
        }
    }

    public void setTableWithNonEditableCell(){
        List<TableColumn<T, ?>> tableColumns = new ArrayList<>();
        table.getColumns().clear();
        for (Field field : type.getDeclaredFields()) {
            TableColumn<T, ?> newTableColumn = createColumn(field.getType(), field.getName());
            tableColumns.add(newTableColumn);
        }
        table.getColumns().addAll(tableColumns);
        init();
    }

    private TableColumn<T, ?> createColumn(Class<?> fieldType, String nameColumn) {
        if (fieldType == SimpleIntegerProperty.class) {
            TableColumn<T, Number> newTableColumn = new TableColumn<>(nameColumn);
            newTableColumn.setCellValueFactory(new PropertyValueFactory<>(nameColumn));
            newTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
            return newTableColumn;
        } else if (fieldType == SimpleDoubleProperty.class) {
            TableColumn<T, Number> newTableColumn = new TableColumn<>(nameColumn);
            newTableColumn.setCellValueFactory(new PropertyValueFactory<>(nameColumn));
            newTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
            return newTableColumn;
        } else {
            TableColumn<T, String> newTableColumn = new TableColumn<>(nameColumn);
            newTableColumn.setCellValueFactory(new PropertyValueFactory<>(nameColumn));
            newTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            return newTableColumn;
        }
    }

    private void invokeMethod(Method method, T objectDTO, String newValue, Field field) throws InvocationTargetException, IllegalAccessException {
        if (field.getType() == SimpleIntegerProperty.class) {
            method.invoke(objectDTO, Integer.parseInt(newValue));
        } else if (field.getType() == SimpleDoubleProperty.class) {
            method.invoke(objectDTO, Double.parseDouble(newValue));
        } else {
            method.invoke(objectDTO, newValue);
        }
    }

    private void editCellEvenHandle(TableColumn.CellEditEvent<T, ?> editEvent) {
        try {
            T objectDTO = editEvent.getRowValue();
            String columnName = editEvent.getTableColumn().getText();
            PropertyDescriptor propertyDescriptor =
                    new PropertyDescriptor(columnName, type);

            invokeMethod(
                    propertyDescriptor.getWriteMethod(),
                    objectDTO,
                    editEvent.getNewValue().toString(),
                    objectDTO.getClass().getDeclaredField(columnName)
            );
            callUpdateMethodForCell(objectDTO);
        } catch (IntrospectionException | IllegalAccessException |
                InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void callUpdateMethodForCell(T objectForUpdate) {
        try {
            Method method = logicObject.getClass().getMethod("update", type);
            method.invoke(logicObject, objectForUpdate);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            Method method = logicObject.getClass().getMethod("findAll");
            List<T> allTObject = (List<T>) method.invoke(logicObject);
            ObservableList<T> dataList = FXCollections.observableArrayList(allTObject);
            table.setItems(dataList);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected TableView<T> getTable(){
        return table;
    }
}
