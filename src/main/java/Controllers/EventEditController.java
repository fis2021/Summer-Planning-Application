package Controllers;

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
    }

    @FXML
    private void handleChooseImageAction(){
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser(".");

        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", new String[] {"JPG", "JPEG"});
        fileChooser.setFileFilter(filter);

        fileChooser.showOpenDialog(frame);
        File selectedFile = fileChooser.getSelectedFile();
        try{
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(selectedFile);
            javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imagePath = selectedFile.getPath();
        }
        catch (Exception e){}
    }

    public void showCurrentEventInfo(Event event){
        Parent root;
        currentEvent = event;
        imagePath = event.getImagePath();
        try{
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Event_Edit.fxml"));
            mLLoader.setController(this);
            root = mLLoader.load();
            Stage stage = new Stage();
            stage.setTitle(event.getName());
            stage.setScene(new Scene(root,1200,800));
            stage.show();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = event.getDate();

            LocalDate localDateObj = LocalDate.parse(dateString, dateTimeFormatter);


            titleTextField.setText(event.getName());
            descriptionTextArea.setText(event.getDescription() + " " + event.getEventID());
            datePicker.setValue(localDateObj);
            priceTextField.setText(String.valueOf(event.getPrice()));

            try {
                BufferedImage bufferedImage;
                bufferedImage = ImageIO.read(new File(event.getImagePath()));
                javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
            }
            catch (Exception e){}
        }
        catch (IOException e){
        }
    }

    public void handleEditAction() {
        if (Objects.equals(editButton.getText(), "Edit")) {
            titleTextField.setEditable(true);
            datePicker.setEditable(true);
            priceTextField.setEditable(true);
            descriptionTextArea.setEditable(true);
            chooseImageButton.setVisible(true);
            cancelButton.setVisible(true);
            editButton.setText("OK");
        }
        else{
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = datePicker.getValue().format(dateFormatter);

            editedEvent = new Event(titleTextField.getText(), descriptionTextArea.getText(),
                    imagePath, Double.parseDouble(priceTextField.getText()), dateString);

            editedEvent.setOrganizatorID(currentEvent.getOrganizatorID());
            editedEvent.setEventID(currentEvent.getEventID());

            EventService.updateEvent(editedEvent);
        }
    }
}