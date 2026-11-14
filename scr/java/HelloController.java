package com.example.javafxej;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label Calculo;

    @FXML
    private Label Resultado;

    private int num1 = 0;
    private String operador = "";
    private boolean nuevoNumero = true;

    @FXML
    protected void Numero(ActionEvent event) {
        String valor = ((Button) event.getSource()).getText();

        if (nuevoNumero || Calculo.getText().equals("0")) {
            Calculo.setText(valor);
            nuevoNumero = false;
        } else {
            Calculo.setText(Calculo.getText() + valor);
        }
    }

    @FXML
    protected void Operador(ActionEvent event) {

        String op = ((Button) event.getSource()).getText();

        if (!operador.isEmpty() && !nuevoNumero) {
            int num2 = Integer.parseInt(Calculo.getText());
            num1 = calcular(num1, num2, operador);
            Calculo.setText(String.valueOf(num1));
        } else {
            num1 = Integer.parseInt(Calculo.getText());
        }

        operador = op;
        Resultado.setText(num1 + " " + operador);
        nuevoNumero = true;
    }

    @FXML
    protected void Igual() {

        int num2 = Integer.parseInt(Calculo.getText());
        if (operador.equals("/") && num2 == 0) {
            mostrarError();
            return;
        }

        int total = calcular(num1, num2, operador);
        Resultado.setText(num1 + " " + operador + " " + num2 + " =");
        Calculo.setText(String.valueOf(total));
        operador = "";
        nuevoNumero = true;
    }

    @FXML
    protected void Limpiar() {
        num1 = 0;
        operador = "";
        nuevoNumero = true;
        Calculo.setText("0");
        Resultado.setText("");
    }

    private int calcular(int a, int b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
                return a * b;
            case "/":
                return a / b;
            default:
                return b;
        }
    }

    private void mostrarError() {
        Calculo.setText("Error");
        Resultado.setText("");
        num1 = 0;
        operador = "";
        nuevoNumero = true;
    }
}
