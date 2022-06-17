package layout.addFriend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddFriendC {

    @FXML
    private TextField name;

    public static void show(Stage owner) {
        try {
            FXMLLoader loader = new FXMLLoader(AddFriendC.class.getResource("AddFriendV.fxml"));
            Parent root = (Parent) loader.load();

            AddFriendC addFriendC = (AddFriendC) loader.getController();

            Stage stage = new Stage();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ThatsApp");
            stage.initOwner(owner);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add(ActionEvent event) {


        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}