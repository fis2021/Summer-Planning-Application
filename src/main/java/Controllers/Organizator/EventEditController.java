package Controllers.Organizator;

import Model.Event;
import Services.EventService;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EventEditController {

    @FXML
    private ImageView imageView;

    @FXML
    private Button editButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button chooseImageButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private GridPane gridPane;

    private Event currentEvent;

    private Event editedEvent;

    private String imagePath;

    @FXML
    public void initialize(){
        titleTextField.setEditable(false);
        datePicker.setEditable(false);
        priceTextField.setEditable(false);
        descriptionTextArea.setEditable(false);
        chooseImageButton.setVisible(false);
        cancelButton.setVisible(false);
        deleteButton.setVisible(false);
    }

    @FXML
    private void handleChooseImageAction(){
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", new String[] {"JPG", "JPEG"});
        fileChooser.setFileFilter(filter);

        fileChooser.showOpenDialog(frame);
        File selectedFile = fileChooser.getSelectedFile();
        try {
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File(imagePath));
            javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }
        catch (Exception e){}
    }

    public void showCurrentEventInfo(Event event){
        currentEvent = event;
        imagePath = event.getImagePath();
        try{
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Organizator/Event_Edit_Organizer.fxml"));
            mLLoader.setController(this);
            Parent root = mLLoader.load();
            Stage stage = new Stage();
            stage.setTitle("asd");
            stage.setScene(new Scene(root,1200,800));
            stage.show();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = event.getDate();

            LocalDate localDateObj = LocalDate.parse(dateString, dateTimeFormatter);


            titleTextField.setText(event.getName());
            descriptionTextArea.setText(event.getDescription());
            datePicker.setValue(localDateObj);
            priceTextField.setText(String.valueOf(event.getPrice()));

            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(new File(imagePath));
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
        catch (IOException e){
        }
    }

    @FXML
    private void handleEditAction() {
        if (Objects.equals(editButton.getText(), "Edit")) {
            titleTextField.setEditable(true);
            datePicker.setEditable(true);
            priceTextField.setEditable(true);
            descriptionTextArea.setEditable(true);
            chooseImageButton.setVisible(true);
            cancelButton.setVisible(true);
            editButton.setText("OK");
            deleteButton.setVisible(true);
        }
        else{
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = datePicker.getValue().format(dateFormatter);

            editedEvent = new Event(titleTextField.getText(), descriptionTextArea.getText(),
                    imagePath, Double.parseDouble(priceTextField.getText()), dateString);

            editedEvent.setOrganizatorID(currentEvent.getOrganizatorID());
            editedEvent.setEventID(currentEvent.getEventID());

            EventService.updateEvent(editedEvent);
            gridPane.getScene().getWindow().hide();
        }
    }

    @FXML
    private void handleDeleteAction(){
        EventService.deleteEvent(editedEvent);
    }

    @FXML
    private void handleCancelAction(){gridPane.getScene().getWindow().hide();}
}