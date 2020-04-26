package sample;

import javafx.application.Application;

import javafx.scene.control.Button;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

import javafx.geometry.Pos;

public class Main extends Application {

    private double value1;
    private String opcode = "";
    private boolean isSet;
    private boolean append = false;

    private double doAdd(double v1, double v2){
        return v1 + v2;
    }

    private double doMinus(double v1, double v2){
        return v1 - v2;
    }

    private double doMultiply(double v1, double v2){
        return v1 * v2;
    }

    private double doDivide(double v1, double v2) throws Exception{

        if (v2 == 0){
            throw new Exception();
        }
        else{
            return v1 / v2;
        }
    }

    private String reset(){
        /*Update all settings to defaults*/
        this.opcode = "";
        this.value1 = 0;
        this.isSet = false;
        this.append = false;

        return "0";
    }

    private String updateDisplay(String displayText, String val){
        /*Adds character to display*/
        if (this.append){
            return displayText + val;
        }
        else if (val.equals(".")) {
            return "0" + val;
        }
        else {
            this.append = true;
            return val;
        }
    }

    private String negate(String displayText){
        /*Returns the negative of the value in the display*/
        return Double.toString(Double.parseDouble(displayText) * -1);
    }


    private String updateDataValues(String displayval, String opcode){
        String res;

        this.append = false;
        if (this.isSet){
            res = calculate(value1, displayval, this.opcode);
            this.value1 = !res.equals("Error") ? Double.parseDouble(res) : 0;
            this.opcode = opcode;
            this.isSet = !res.equals("Error");

            return res;
        }
        else{
            this.value1 = Double.parseDouble(displayval);
            this.opcode = opcode;
            this.isSet = true;
            return displayval;
        }
    }

    private String calculate(double v1, String val, String opcode){
        double v2 = Double.parseDouble(val);

        reset();
        switch (opcode){
            case "+":
                return formatOutput(doAdd(v1, v2));

            case "-":
                return formatOutput(doMinus(v1, v2));

            case "x":
                return formatOutput(doMultiply(v1, v2));

            case "/":
                try{
                    return formatOutput(doDivide(v1, v2));
                }
                catch (Exception e){
                    return "Error";
                }
            case "":
                return val;

            default:
                return "Error";
        }
    }

    private String formatOutput(double value){
        /*Formats output for display*/
        DecimalFormat df = new DecimalFormat("#.######");
        return df.format(value);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        final int minWidth = 50;
        final int minHeight = 50;
        final int prefWidth = 1000;
        final int prefHeight = 800;

        VBox root = new VBox();
        primaryStage.setTitle("MyCalculator");

        final String defaultText = "0";
        TextField display = new TextField(defaultText);
        display.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();

        Button bclear = MyButton.buildButton("A/C", minHeight, minWidth, prefHeight, prefWidth);
        bclear.setOnAction(i -> display.setText(reset()));
        grid.add(bclear, 0, 0, 1, 1);

        Button bneg = MyButton.buildButton("+/-", minHeight, minWidth, prefHeight, prefWidth);
        bneg.setOnAction(actionEvent -> display.setText(negate(display.getText())));
        grid.add(bneg, 1, 0, 1, 1);

        Button bsquare = MyButton.buildButton("x²", minHeight, minWidth, prefHeight, prefWidth);
        bsquare.setOnAction(actionEvent -> display.setText(Double.toString(
                Math.pow(Double.parseDouble(display.getText()), 2))));
        grid.add(bsquare, 2, 0, 1, 1);

        Button bsqrt = MyButton.buildButton("\u221Ax", minHeight, minWidth, prefHeight, prefWidth);
        bsqrt.setOnAction(actionEvent -> display.setText(Double.toString(
                Math.pow(Double.parseDouble(display.getText()), .5))));
        grid.add(bsqrt, 3, 0, 1, 1);


        Button b7 = MyButton.buildButton("7", minHeight, minWidth, prefHeight, prefWidth);
        b7.setOnAction(actionEvent -> display.setText(
                updateDisplay(display.getText(), b7.getText())));
        grid.add(b7, 0, 1, 1, 1);

        Button b8 = MyButton.buildButton("8", minHeight, minWidth, prefHeight, prefWidth);
        b7.setOnAction(actionEvent -> display.setText(
                updateDisplay(display.getText(), b8.getText())));
        grid.add(b8, 1, 1, 1, 1);

        Button b9 = MyButton.buildButton("9", minHeight, minWidth, prefHeight, prefWidth);
        b9.setOnAction(actionEvent -> display.setText(
                updateDisplay(display.getText(), b9.getText())));
        grid.add(b9, 2, 1, 1, 1);

        Button bplus = MyButton.buildButton("+", minHeight, minWidth, prefHeight, prefWidth);
        bplus.setOnAction(i -> display.setText(updateDataValues(display.getText(), bplus.getText())));
        grid.add(bplus, 3, 1, 1, 1);


        Button b4 = MyButton.buildButton("4", minHeight, minWidth, prefHeight, prefWidth);
        b4.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b4.getText())));
        grid.add(b4, 0, 2, 1, 1);

        Button b5 = MyButton.buildButton("5", minHeight, minWidth, prefHeight, prefWidth);
        b5.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b5.getText())));
        grid.add(b5, 1, 2, 1, 1);

        Button b6 = MyButton.buildButton("6", minHeight, minWidth, prefHeight, prefWidth);
        b6.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b6.getText())));
        grid.add(b6, 2, 2, 1, 1);

        Button bminus = MyButton.buildButton("-", minHeight, minWidth, prefHeight, prefWidth);
        bminus.setOnAction(i -> display.setText(updateDataValues(display.getText(), bminus.getText())));
        grid.add(bminus, 3, 2, 1, 1);

        Button b1 = MyButton.buildButton("1", minHeight, minWidth, prefHeight, prefWidth);
        b1.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b1.getText())));
        grid.add(b1, 0, 3, 1, 1);

        Button b2 = MyButton.buildButton("2", minHeight, minWidth, prefHeight, prefWidth);
        b2.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b2.getText())));
        grid.add(b2, 1, 3, 1, 1);

        Button b3 = MyButton.buildButton("3", minHeight, minWidth, prefHeight, prefWidth);
        b3.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b3.getText())));
        grid.add(b3, 2, 3, 1, 1);

        Button bmult = MyButton.buildButton("x", minHeight, minWidth, prefHeight, prefWidth);
        bmult.setOnAction(i -> display.setText(updateDataValues(display.getText(), bmult.getText())));
        grid.add(bmult, 3, 3, 1, 1);


        Button bper = MyButton.buildButton(".", minHeight, minWidth, prefHeight, prefWidth);
        bper.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), bper.getText())));
        grid.add(bper, 0, 4, 1, 1);
        Button b0 = MyButton.buildButton("0", minHeight, minWidth, prefHeight, prefWidth);
        b0.setOnAction(i -> display.setText(
                updateDisplay(display.getText(), b0.getText())));
        grid.add(b0, 1, 4, 1, 1);

        Button bequ = MyButton.buildButton("=", minHeight, minWidth, prefHeight, prefWidth);
        bequ.setOnAction(i -> display.setText(calculate(this.value1, display.getText(), this.opcode)));
        grid.add(bequ, 2, 4, 1, 1);

        Button bdiv = MyButton.buildButton("/", minHeight, minWidth, prefHeight, prefWidth);
        bdiv.setOnAction(i -> display.setText(updateDataValues(display.getText(), bdiv.getText())));
        grid.add(bdiv, 3, 4, 1, 1);

        root.getChildren().addAll(display, grid);

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
