package Functionality;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Styles {


        public static MFXButton DashboardButton(String buttonLabel){
            MFXButton button = new MFXButton(buttonLabel);
            button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            button.setAlignment(Pos.CENTER);
            button.setTextFill(Color.WHITE);
            button.setPrefWidth(200);
            button.setOnMouseEntered(e->{
                button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
                button.setTextFill(Color.web("#02557a"));
            });
            button.setOnMouseExited(e->{
                button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
                button.setTextFill(Color.WHITE);
            });
            return button;
        }

    public static MFXTextField textField(String fieldLabel) {
        MFXTextField textField = new MFXTextField();
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setMinSize(150, 40);
        textField.setFloatingText(fieldLabel);
        return textField;
    }
}
