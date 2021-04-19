import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Event/Event_Create.fxml"));
        stage.setTitle("EventCreateUI");
        stage.setScene(new Scene(root,1200,800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
