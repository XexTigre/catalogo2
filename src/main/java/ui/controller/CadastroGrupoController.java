package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class CadastroGrupoController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField ordemField;
    @FXML
    private CheckBox visivelMenuCheck;

    @FXML
    public void onSalvar() {
        nomeField.clear();
        ordemField.clear();
        visivelMenuCheck.setSelected(false);
    }
}
