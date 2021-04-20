package Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;

public class EventListViewCell extends ListCell<Event> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView imageView;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Event event, boolean empty) {
        super.updateItem(event, empty);

        if (empty || event == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Event_List_Cell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            titleLabel.setText(event.getName());
            descriptionLabel.setText(event.getDescription());
            dateLabel.setText(event.getDate());
            priceLabel.setText(String.valueOf(event.getPrice()));

            try {
                BufferedImage bufferedImage;
                bufferedImage = ImageIO.read(new File(event.getImagePath()));
                javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
            }
            catch (Exception e){ descriptionLabel.setText("fes");};

            setText(null);
            setGraphic(gridPane);
        }
    }
}
