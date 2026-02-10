package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroMontadoraController {
    @FXML
    private TextField nomeField;

    @FXML
    public void onSalvar() {
        nomeField.clear();
    }
}
