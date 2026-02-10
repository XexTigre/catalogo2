package ui.controller;

import app.Router;
import domain.service.PesquisaService;
import infra.db.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PesquisaController {
    @FXML
    private TextField codigoField;
    @FXML
    private TextField descricaoField;
    @FXML
    private ComboBox<String> marcaCombo;
    @FXML
    private ComboBox<String> grupoCombo;

    private final PesquisaService pesquisaService = new PesquisaService(DatabaseManager.getInstance());

    @FXML
    public void initialize() {
        marcaCombo.setItems(FXCollections.observableArrayList("Todas"));
        grupoCombo.setItems(FXCollections.observableArrayList("Todos"));
        marcaCombo.getSelectionModel().selectFirst();
        grupoCombo.getSelectionModel().selectFirst();
    }

    @FXML
    public void onPesquisar() {
        boolean filtrosVazios = (codigoField.getText() == null || codigoField.getText().isBlank())
                && (descricaoField.getText() == null || descricaoField.getText().isBlank())
                && (marcaCombo.getSelectionModel().getSelectedIndex() <= 0)
                && (grupoCombo.getSelectionModel().getSelectedIndex() <= 0);

        if (filtrosVazios) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                    "Nenhum filtro foi informado. Deseja ver todos os itens?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isEmpty() || resultado.get() == ButtonType.NO) {
                return;
            }
        }

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("codigo", codigoField.getText());
        filtros.put("descricao", descricaoField.getText());
        filtros.put("marca", marcaCombo.getValue());
        filtros.put("grupo", grupoCombo.getValue());
        Router.showConsulta(filtros);
    }

    @FXML
    public void onAbrirCadastroProduto() {
        Router.showCadastroProduto();
    }

    @FXML
    public void onAbrirCadastrosBase() {
        Router.showCadastroMarca();
    }
}
