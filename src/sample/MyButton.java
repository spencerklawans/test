package sample;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


class MyButton {
    static Button buildButton(String text, int minH, int minW, int prefH, int prefW){
        Button newButton = new Button(text);
        newButton.setMinSize(minW, minH);
        newButton.setPrefSize(prefW, prefH);

        return newButton;
    }
}
