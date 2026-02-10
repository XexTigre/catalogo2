package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroBaseController {
    @FXML
    private TextField nomeField;

    @FXML
    public void onSalvar() {
        if (nomeField != null) {
            nomeField.clear();
        }
    }
}
