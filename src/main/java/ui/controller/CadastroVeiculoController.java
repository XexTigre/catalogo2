package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroVeiculoController {
    @FXML
    private TextField montadoraField;
    @FXML
    private TextField modeloField;
    @FXML
    private TextField versaoField;

    @FXML
    public void onSalvar() {
        montadoraField.clear();
        modeloField.clear();
        versaoField.clear();
    }
}
