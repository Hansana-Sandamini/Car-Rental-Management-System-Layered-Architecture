package lk.ijse.rdfcarrentals.util;

import javafx.scene.image.ImageView;

public class OptionButtonsUtil {

    public static ImageView setRemoveButton() {
        ImageView btnRemove = new ImageView(String.valueOf(OptionButtonsUtil.class.getResource("/assets/images/RemoveButton.png")));
        btnRemove.setFitHeight(24);
        btnRemove.setFitWidth(24);
        btnRemove.setStyle("-fx-cursor: hand; -fx-alignment: center-right");
        return btnRemove;
    }

    public static ImageView setUpdateButton() {
        ImageView btnUpdate = new ImageView(String.valueOf(OptionButtonsUtil.class.getResource("/assets/images/UpdateButton.png")));
        btnUpdate.setFitHeight(24);
        btnUpdate.setFitWidth(24);
        btnUpdate.setStyle("-fx-cursor: hand; -fx-alignment: center-right");
        return btnUpdate;
    }
}
