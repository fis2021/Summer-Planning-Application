import Services.EventService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import Services.FileSystemService;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application{

    @Override
    public void start(Stage primarystage) throws Exception{
        initDirectory();
        EventService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/ContainerWindows/Organizator_Container.fxml"));
        primarystage.setTitle("ORGORG");
        primarystage.setScene(new Scene(root,1440,800));
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
