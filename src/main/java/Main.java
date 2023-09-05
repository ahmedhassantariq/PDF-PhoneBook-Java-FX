import Functionality.BookTable;
import Functionality.Controller;
import Functionality.StorageHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        StorageHandler.retrieveData();
        Scene scene = new Scene(BookTable.bookTable(),500,550);
        stage.setScene(scene);
        stage.setTitle("Phone Book");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
