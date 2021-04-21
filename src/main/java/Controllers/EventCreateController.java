package Controllers;

import Model.Event;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import Services.EventService;

public class EventCreateController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

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
        try{
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(selectedFile);
            javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagePreView.setImage(image);
            imagePath = selectedFile.getPath();
        }
        catch (Exception e){}
    }

    @FXML
    private void handleOkAction(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateString = datePicker.getValue().format(dateFormatter);
        EventService.addEvent(new Event(nameField.getText(), descriptionField.getText(),
                imagePath, Double.parseDouble(priceField.getText()), dateString));
    }

    @FXML
    private void handleCancelAction(){
        Platform.exit();
        //System.exit(0);
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

    public void setDescriptionField(TextField descriptionField) {
        this.descriptionField = descriptionField;
    }
    public TextField getDescriptionField() {
        return descriptionField;
    }
}

