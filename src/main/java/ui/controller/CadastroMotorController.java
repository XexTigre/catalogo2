package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroMotorController {
    @FXML
    private TextField montadoraField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField codigoField;

    @FXML
    public void onSalvar() {
        montadoraField.clear();
        nomeField.clear();
        codigoField.clear();
    }
}
