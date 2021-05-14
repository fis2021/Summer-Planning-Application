package Controllers.Organizator;

import Exceptions.InvalidEventDetailsException;
import Model.Event;
import Services.UserService;
import Validators.ValidateEventDetails;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import Services.EventService;

public class EventCreateController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView imagePreView;


    private String imagePath;

    @FXML
    private void handleChooseImageAction(){
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser(".");

        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", new String[] {"JPG", "JPEG"});
        fileChooser.setFileFilter(filter);

        fileChooser.showOpenDialog(frame);
        File selectedFile = fileChooser.getSelectedFile();
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
            javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagePreView.setImage(image);
        }
        catch (Exception e){
            try {
                bufferedImage = ImageIO.read(new File("D:/UNI/Sem2/FIS/FIS_Project/src/main/resources/Images/404.png"));
                javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imagePreView.setImage(image);
            }
            catch(IOException ex){}
        }
    }

    @FXML
    private void handleOkAction(){
        try {
            ValidateEventDetails VED = new ValidateEventDetails();
            VED.validateAll(nameField.getText() ,imagePath, priceField.getText(), datePicker.getValue());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = datePicker.getValue().format(dateFormatter);
            Event event = new Event(nameField.getText(), descriptionArea.getText(),
                    imagePath, Double.parseDouble(priceField.getText()), dateString);
            event.setOrganizatorID(UserService.getMainUser().getUserID());
            EventService.addEvent(event);

            nameField.clear();
            descriptionArea.clear();
            imagePreView.imageProperty().set(null);
            priceField.clear();
            datePicker.setValue(null);
        }
        catch (InvalidEventDetailsException e){showMessage(e.getMessage());}
    }

    private void showMessage(String msg){
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setPriceField(TextField priceField) {
        this.priceField = priceField;
    }
    public TextField getPriceField() {
        return priceField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }
    public TextField getNameField() {
        return nameField;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }
    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDescriptionArea(TextArea descriptionArea) {
        this.descriptionArea = descriptionArea;
    }
    public TextArea getDescriptionArea() {
        return descriptionArea;
    }
}

