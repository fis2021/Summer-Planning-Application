import Model.Event;
import Services.EventService;
import Services.ReservationService;
import Services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

import static Services.ReservationService.initDatabase;
import static javafx.application.Application.launch;
import Services.FileSystemService;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application{

    @Override
    public void start(Stage primarystage) throws Exception{
        initDirectory();
        EventService.initDatabase();
        UserService.initDatabase();
        initDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/UserUI/Login.fxml"));
        primarystage.setTitle("Login");
        primarystage.setScene(new Scene(root,600,400));
        primarystage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
