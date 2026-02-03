package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroMarcaController {
    @FXML
    private TextField nomeField;

    @FXML
    public void onSalvar() {
        nomeField.clear();
    }
}
