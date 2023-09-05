package Functionality;

import Entities.Person;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class BookTable {
    public static TableView<Person> bookTable = new TableView<>();

    public static Parent bookTable(){
        bookTable.setItems(Controller.personObservableList);
        TableColumn<Person, String> firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        firstNameCol.setMinWidth(50);
        firstNameCol.setResizable(false);

        TableColumn<Person, String> middleNameCol = new TableColumn("Middle Name");
        middleNameCol.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        middleNameCol.setMinWidth(50);
        middleNameCol.setResizable(false);

        TableColumn<Person, String> lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        lastNameCol.setMinWidth(50);
        lastNameCol.setResizable(false);

        TableColumn<Person, String> phoneCol = new TableColumn("Phone Number");
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneNoProperty());
        phoneCol.setMinWidth(100);
        phoneCol.setResizable(false);

        TableColumn<Person, String> areaCodeCol = new TableColumn("Area-Code");
        areaCodeCol.setCellValueFactory(cellData -> cellData.getValue().areaCodeProperty());
        areaCodeCol.setMinWidth(50);
        areaCodeCol.setResizable(false);

        TableColumn<Person, String> addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        addressCol.setMinWidth(100);
        bookTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bookTable.getColumns().addAll(firstNameCol,middleNameCol,lastNameCol,phoneCol,areaCodeCol,addressCol);



        ScrollPane scrollPane = new ScrollPane(bookTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(bookTable.getPrefWidth());

        VBox mainBox = new VBox(scrollPane,Controller.controller());
        return mainBox;


    }
}
