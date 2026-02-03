package ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastroProdutoController {
    @FXML
    private VBox referenciasContainer;
    @FXML
    private VBox aplicacoesContainer;

    @FXML
    public void initialize() {
        adicionarReferencia();
        adicionarAplicacao();
    }

    @FXML
    public void onAdicionarReferencia() {
        adicionarReferencia();
    }

    @FXML
    public void onAdicionarAplicacao() {
        adicionarAplicacao();
    }

    private void adicionarReferencia() {
        HBox linha = new HBox(8);
        ComboBox<String> tipo = new ComboBox<>(FXCollections.observableArrayList("ORIGINAL", "SIMILAR"));
        tipo.getSelectionModel().selectFirst();
        TextField marca = new TextField();
        marca.setPromptText("Marca");
        TextField nome = new TextField();
        nome.setPromptText("Nome");
        TextField codigo = new TextField();
        codigo.setPromptText("Código");
        TextField fonte = new TextField();
        fonte.setPromptText("Fonte URL");
        Button remover = new Button("Remover");
        remover.setOnAction(event -> referenciasContainer.getChildren().remove(linha));
        linha.getChildren().addAll(tipo, marca, nome, codigo, fonte, remover);
        referenciasContainer.getChildren().add(linha);
    }

    private void adicionarAplicacao() {
        HBox linha = new HBox(8);
        TextField montadora = new TextField();
        montadora.setPromptText("Montadora");
        TextField veiculo = new TextField();
        veiculo.setPromptText("Veículo");
        TextField motor = new TextField();
        motor.setPromptText("Motor");
        TextField anoIni = new TextField();
        anoIni.setPromptText("Ano Inicial");
        TextField anoFim = new TextField();
        anoFim.setPromptText("Ano Final");
        TextField observacao = new TextField();
        observacao.setPromptText("Observação");
        Button remover = new Button("Remover");
        remover.setOnAction(event -> aplicacoesContainer.getChildren().remove(linha));
        linha.getChildren().addAll(montadora, veiculo, motor, anoIni, anoFim, observacao, remover);
        aplicacoesContainer.getChildren().add(linha);
    }
}
