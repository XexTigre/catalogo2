package ui.controller;

import app.Router;
import domain.enums.ProdutoImagemTipo;
import domain.model.Produto;
import domain.model.ProdutoImagem;
import domain.model.ProdutoAplicacao;
import domain.model.ProdutoReferencia;
import domain.service.PesquisaService;
import domain.service.ProdutoService;
import infra.db.DatabaseManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Map;

public class ConsultaController {
    @FXML
    private Label filtroLabel;
    @FXML
    private TableView<Produto> produtosTable;
    @FXML
    private TableColumn<Produto, String> codigoColumn;
    @FXML
    private TableColumn<Produto, String> descricaoColumn;
    @FXML
    private TableColumn<Produto, String> lancamentoColumn;
    @FXML
    private TableColumn<Produto, Void> figuraColumn;
    @FXML
    private TableColumn<Produto, Void> relacionadoColumn;
    @FXML
    private StackPane listaStack;
    @FXML
    private StackPane relacionadosView;
    @FXML
    private Label relacionadosTitulo;
    @FXML
    private TableView<Produto> relacionadosTable;
    @FXML
    private TableColumn<Produto, String> relacionadosCodigoColumn;
    @FXML
    private TableColumn<Produto, String> relacionadosDescricaoColumn;
    @FXML
    private TableView<ProdutoReferencia> referenciasTable;
    @FXML
    private TableColumn<ProdutoReferencia, String> referenciaTipoColumn;
    @FXML
    private TableColumn<ProdutoReferencia, String> referenciaMarcaColumn;
    @FXML
    private TableColumn<ProdutoReferencia, String> referenciaNomeColumn;
    @FXML
    private TableColumn<ProdutoReferencia, String> referenciaCodigoColumn;
    @FXML
    private TableColumn<ProdutoReferencia, String> referenciaFonteColumn;
    @FXML
    private TableView<ProdutoAplicacao> aplicacoesTable;
    @FXML
    private TableColumn<ProdutoAplicacao, String> aplicacaoMontadoraColumn;
    @FXML
    private TableColumn<ProdutoAplicacao, String> aplicacaoVeiculoColumn;
    @FXML
    private TableColumn<ProdutoAplicacao, String> aplicacaoMotorColumn;
    @FXML
    private TableColumn<ProdutoAplicacao, String> aplicacaoAnoColumn;
    @FXML
    private TableColumn<ProdutoAplicacao, String> aplicacaoObsColumn;
    @FXML
    private Label specsLabel;
    @FXML
    private ImageView produtoImagemView;
    @FXML
    private ListView<String> sugestoesList;

    private final ProdutoService produtoService = new ProdutoService(DatabaseManager.getInstance());
    private final PesquisaService pesquisaService = new PesquisaService(DatabaseManager.getInstance());

    public void setFiltros(Map<String, Object> filtros) {
        String descricaoFiltro = String.format("Código: %s | Descrição: %s | Marca: %s | Grupo: %s",
                filtros.getOrDefault("codigo", ""),
                filtros.getOrDefault("descricao", ""),
                filtros.getOrDefault("marca", ""),
                filtros.getOrDefault("grupo", ""));
        filtroLabel.setText(descricaoFiltro);

        List<Produto> resultados = pesquisaService.pesquisar(
                (String) filtros.get("codigo"),
                (String) filtros.get("descricao"),
                null,
                null
        );
        produtosTable.setItems(FXCollections.observableArrayList(resultados));
    }

    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        descricaoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
        lancamentoColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getDataLancamento() == null ? "" : data.getValue().getDataLancamento().toString()));

        figuraColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Ver");

            {
                button.setOnAction(event -> {
                    Produto produto = getTableView().getItems().get(getIndex());
                    String caminho = buscarFigura(produto.getId());
                    Router.showFiguraModal(produto.getCodigo(), caminho);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
            }
        });

        relacionadoColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Ver");

            {
                button.setOnAction(event -> mostrarRelacionados(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
            }
        });

        produtosTable.getSelectionModel().selectedItemProperty().addListener((obs, old, novo) -> {
            if (novo != null) {
                carregarDetalhes(novo);
            }
        });

        relacionadosCodigoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        relacionadosDescricaoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));

        referenciaTipoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipo().name()));
        referenciaMarcaColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getMarcaId() == null ? "" : data.getValue().getMarcaId().toString()));
        referenciaNomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        referenciaCodigoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        referenciaFonteColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFonteUrl()));

        aplicacaoMontadoraColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getMontadoraId() == null ? "" : data.getValue().getMontadoraId().toString()));
        aplicacaoVeiculoColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getVeiculoId() == null ? "" : data.getValue().getVeiculoId().toString()));
        aplicacaoMotorColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getMotorId() == null ? "" : data.getValue().getMotorId().toString()));
        aplicacaoAnoColumn.setCellValueFactory(data -> new SimpleStringProperty(
                formatAno(data.getValue().getAnoInicial(), data.getValue().getAnoFinal())));
        aplicacaoObsColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacao()));

        relacionadosView.setVisible(false);
        relacionadosView.setManaged(false);
    }

    private void carregarDetalhes(Produto produto) {
        List<ProdutoReferencia> referencias = produtoService.listarReferencias(produto.getId());
        referenciasTable.setItems(FXCollections.observableArrayList(referencias));

        List<ProdutoAplicacao> aplicacoes = produtoService.listarAplicacoes(produto.getId());
        aplicacoesTable.setItems(FXCollections.observableArrayList(aplicacoes));

        specsLabel.setText(produto.getObservacao() == null ? "Sem observações" : produto.getObservacao());
        List<ProdutoImagem> imagens = produtoService.listarImagens(produto.getId());
        ProdutoImagem produtoImagem = imagens.stream()
                .filter(img -> img.getTipo() == ProdutoImagemTipo.PRODUTO)
                .findFirst()
                .orElse(null);
        if (produtoImagem != null && produtoImagem.getCaminho() != null) {
            produtoImagemView.setImage(new Image("file:" + produtoImagem.getCaminho()));
        } else {
            produtoImagemView.setImage(null);
        }

        List<Produto> sugestoes = produtoService.listarSugestoes(produto.getId());
        sugestoesList.setItems(FXCollections.observableArrayList(
                sugestoes.stream().map(Produto::getDescricao).toList()
        ));
    }

    private String buscarFigura(Long produtoId) {
        return produtoService.listarImagens(produtoId).stream()
                .filter(img -> img.getTipo() == ProdutoImagemTipo.FIGURA)
                .map(ProdutoImagem::getCaminho)
                .findFirst()
                .orElse(null);
    }

    private void mostrarRelacionados(Produto produto) {
        relacionadosTitulo.setText("Produtos Relacionados ao produto: " + produto.getCodigo());
        relacionadosTable.setItems(FXCollections.observableArrayList(
                produtoService.listarRelacionadosDiretos(produto.getId())));
        relacionadosView.setVisible(true);
        relacionadosView.setManaged(true);
    }

    private String formatAno(Integer inicio, Integer fim) {
        if (inicio == null && fim == null) {
            return "";
        }
        if (inicio != null && fim != null) {
            return inicio + " - " + fim;
        }
        return inicio != null ? inicio.toString() : fim.toString();
    }

    @FXML
    public void onVoltarRelacionados() {
        relacionadosView.setVisible(false);
        relacionadosView.setManaged(false);
    }

    @FXML
    public void onNovaPesquisa() {
        Router.showPesquisa();
    }
}
