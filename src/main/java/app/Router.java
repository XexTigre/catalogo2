package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Router {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void showPesquisa() {
        loadInPrimary("/ui/view/pesquisa.fxml", "Pesquisa de Produtos");
    }

    public static void showConsulta(Map<String, Object> filtros) {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource("/ui/view/consulta_produtos.fxml"));
        try {
            Parent root = loader.load();
            ui.controller.ConsultaController controller = loader.getController();
            controller.setFiltros(filtros);
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.setTitle("Consulta de Produtos");
            primaryStage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível abrir consulta", e);
        }
    }

    public static void showCadastroProduto() {
        loadInPrimary("/ui/view/cadastro_produto.fxml", "Cadastro de Produto");
    }

    public static void showCadastroMarca() {
        loadInPrimary("/ui/view/cadastro_marca.fxml", "Cadastro de Marca");
    }

    public static void showCadastroGrupo() {
        loadInPrimary("/ui/view/cadastro_grupo.fxml", "Cadastro de Grupo");
    }

    public static void showCadastroMontadora() {
        loadInPrimary("/ui/view/cadastro_montadora.fxml", "Cadastro de Montadora");
    }

    public static void showCadastroVeiculo() {
        loadInPrimary("/ui/view/cadastro_veiculo.fxml", "Cadastro de Veículo");
    }

    public static void showCadastroMotor() {
        loadInPrimary("/ui/view/cadastro_motor.fxml", "Cadastro de Motor");
    }

    public static void showFiguraModal(String codigo, String caminho) {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource("/ui/view/figura_modal.fxml"));
        try {
            Parent root = loader.load();
            ui.controller.FiguraModalController controller = loader.getController();
            controller.setDados(codigo, caminho);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Figura - " + codigo);
            stage.setScene(new Scene(root, 800, 600));
            stage.showAndWait();
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível abrir figura", e);
        }
    }

    private static void loadInPrimary(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(Router.class.getResource(fxml));
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao carregar tela", e);
        }
    }
}
