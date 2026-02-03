package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class FiguraModalController {
    @FXML
    private StackPane container;
    @FXML
    private ImageView figuraImageView;
    @FXML
    private Label mensagemLabel;

    public void setDados(String codigo, String caminho) {
        if (caminho == null || caminho.isBlank()) {
            mensagemLabel.setText("Sem figura disponível");
            figuraImageView.setImage(null);
        } else {
            mensagemLabel.setText("");
            figuraImageView.setImage(new Image("file:" + caminho));
        }
    }

    @FXML
    public void onFechar() {
        container.getScene().getWindow().hide();
    }
}
