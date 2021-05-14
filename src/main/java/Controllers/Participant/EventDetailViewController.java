package Controllers.Participant;

import Model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailViewController {
    @FXML
    private ImageView imageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    public void showEvent(Event event){
        Parent root;
        try{
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Event_Detail_View.fxml"));
            mLLoader.setController(this);
            root = mLLoader.load();
            Stage stage = new Stage();
            stage.setTitle(event.getName());
            stage.setScene(new Scene(root,1200,800));
            stage.show();

            titleLabel.setText(event.getName());
            descriptionLabel.setText(event.getDescription());
            dateLabel.setText(event.getEventID());
            priceLabel.setText(String.valueOf(event.getPrice()));

            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(new File(event.getImagePath()));
                javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
            }
            catch (Exception e){
                try {
                    bufferedImage = ImageIO.read(new File("D:/UNI/Sem2/FIS/FIS_Project/src/main/resources/Images/404.png"));
                    javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(image);
                }
                catch(IOException ex){}
            }
        }
        catch (IOException e){}

    }

}
