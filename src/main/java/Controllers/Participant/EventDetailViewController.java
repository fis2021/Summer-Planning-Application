package Controllers.Participant;

import Model.Event;
import Model.Reservation;
import Services.ReservationService;
import Services.UserService;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    private Event detailedEvent;

    public void showEvent(Event event){
        detailedEvent = event;

        Parent root;
        try{
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Participant/Event_Detail_View_Participant.fxml"));
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

    @FXML
    private void handleReserveAction(){
        ReservationService.addReservation(new Reservation(detailedEvent.getEventID(), UserService.getMainUser().getUserID()));
        titleLabel.getScene().getWindow().hide();
    }
}
