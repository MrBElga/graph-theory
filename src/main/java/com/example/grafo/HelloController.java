package com.example.grafo;

import com.example.grafo.graficos.GraphVisualization;
import com.example.grafo.graficos.ListaVisualization;
import com.example.grafo.graficos.MatrizVisualization;
import com.example.grafo.lista.Lista;
import com.example.grafo.matriz.Matriz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloController {

    @FXML
    private TextField fileNameField;

    @FXML
    private StackPane graphPane;

    @FXML
    private StackPane graphPane1;

    @FXML
    private StackPane graphPane2;

    @FXML
    private ChoiceBox<String> select;

    @FXML
    private Button convertButton;

    private static Lista[] listaAdjacencia;
    public static String[] rotulos;
    private static int[][] matrizAdjacencia;
    private String nomeArquivo = null;
    private boolean isMatriz = true; // Controla o formato atual

    @FXML
    protected void onHelloButtonClick() throws IOException {
        nomeArquivo = fileNameField.getText();
        String escolha = select.getValue();
        if("Matriz".equals(escolha)) {
            leitor(nomeArquivo,true);
            updateDisplay();
        }else{
            leitor(nomeArquivo,false);
            updateDisplay();
        }
    }

    @FXML
    protected void onClearButtonClick() {
        if (graphPane != null)
            graphPane.getChildren().clear();
        if (graphPane1 != null)
            graphPane1.getChildren().clear();
        if (graphPane2 != null)
            graphPane2.getChildren().clear();
        nomeArquivo = null;
        listaAdjacencia = null;
        matrizAdjacencia = null;
    }

    @FXML
    protected void onConvertButtonClick() {
        if (isMatriz) {
            // Converter matriz para lista
            Lista lista = new Lista();
            listaAdjacencia = Lista.matrizParaLista(matrizAdjacencia, rotulos);
            isMatriz = false;
            select.setValue("Lista");
            lista.analisarGrafoL(listaAdjacencia);

        } else {
            // Converter lista para matriz
            Matriz matriz = new Matriz();
            matrizAdjacencia = Matriz.listaParaMatriz(listaAdjacencia, rotulos);
            isMatriz = true;
            select.setValue("Matriz");
            matriz.analisarGrafo(matrizAdjacencia);
        }
        graphPane.getChildren().clear();
        graphPane1.getChildren().clear();
        graphPane2.getChildren().clear();
        updateDisplay();
    }

    private void updateDisplay() {
        String escolha = select.getValue();
        if ("Matriz".equals(escolha)) {
            if (graphPane != null) {
                graphPane.getChildren().clear();
                GraphVisualization visualizacaoGrafo = new GraphVisualization(matrizAdjacencia, rotulos,null);
                graphPane.getChildren().add(visualizacaoGrafo);
            }
            if (graphPane1 != null) {
                graphPane1.getChildren().clear();
                MatrizVisualization visualizacaoMatriz = new MatrizVisualization(matrizAdjacencia, rotulos);
                graphPane1.getChildren().add(visualizacaoMatriz);
                graphPane1.setStyle("-fx-padding: 10; -fx-alignment: center-left; -fx-background-color: #363636; -fx-border-color: WHITE");
            }
            if (graphPane2 != null) {
                graphPane2.getChildren().clear();

                StringBuilder analise = new StringBuilder();

                analise.append("Grafo Orientado: ").append(Matriz.grafoOrientado(matrizAdjacencia) ? "Sim" : "Não").append("\n");
                analise.append("Grafo Simples: ").append(Matriz.grafoSimples(matrizAdjacencia) ? "Sim" : "Não").append("\n");
                analise.append("Grafo Regular: ").append(Matriz.grafoRegular(matrizAdjacencia) ? "Sim" : "Não").append("\n");
                if(Matriz.grafoOrientado(matrizAdjacencia)){
                    boolean regularDeEmissao = Matriz.grafoRegularDeEmissao(matrizAdjacencia);
                    boolean regularDeTransmissao = Matriz.grafoRegularDeTransmissao(matrizAdjacencia);
                    if(regularDeEmissao && regularDeTransmissao){
                        analise.append("└──regular de emissão e transmissão").append("\n");
                        System.out.println("O grafo é regular de emissão e transmissão.");
                    }
                    else if (regularDeEmissao) {
                        analise.append("└──regular de emissão").append("\n");
                        System.out.println("O grafo é regular de emissão.");
                    } else if (regularDeTransmissao) {
                        analise.append("└──regular de transmissão").append("\n");
                        System.out.println("O grafo é regular de transmissão.");
                    } else {
                        analise.append("└──não regular de emissão nem de transmissão").append("\n");
                        System.out.println("O grafo não é regular de emissão nem de transmissão.");
                    }
                }
                analise.append("Grafo Completo: ").append(Matriz.grafoCompleto(matrizAdjacencia) ? "Sim" : "Não").append("\n");
                System.out.println("---------------------------");
                Text analiseText = new Text(analise.toString());
                analiseText.setFont(new Font("Arial", 14));
                analiseText.setFill(Color.web("#ACACAC"));

                graphPane2.getChildren().add(analiseText);
                graphPane2.setStyle("-fx-padding: 10; -fx-alignment: center-left; -fx-background-color: #363636; -fx-border-color: WHITE");
            }
        } else if ("Lista".equals(escolha)) {
            if (graphPane != null) {
                graphPane.getChildren().clear();
                //ARRUMAR
                GraphVisualization visualizacaoGrafo = new GraphVisualization(null, rotulos,listaAdjacencia);
                graphPane.getChildren().add(visualizacaoGrafo);
            }
            if (graphPane1 != null) {
                graphPane1.getChildren().clear();
                ListaVisualization visualizacaoLista = new ListaVisualization(listaAdjacencia, rotulos);
                graphPane1.getChildren().add(visualizacaoLista);
                graphPane1.setStyle("-fx-padding: 10; -fx-alignment: center-left; -fx-background-color: #363636; -fx-border-color: WHITE");
            }
            if (graphPane2 != null) {
                graphPane2.getChildren().clear();
                StringBuilder analise = new StringBuilder();
                //ARRUMAR
                //analise.append("Grafo Orientado: ").append(Lista.grafoOrientado(listaAdjacencia,rotulos) ? "Sim" : "Não").append("\n");
                //analise.append("Grafo Simples: ").append(Lista.grafoSimples(listaAdjacencia,rotulos) ? "Sim" : "Não").append("\n");
                //analise.append("Grafo Regular: ").append(Lista.grafoRegular(listaAdjacencia) ? "Sim" : "Não").append("\n");
                //analise.append("Grafo Completo: ").append(Lista.grafoCompleto(listaAdjacencia,rotulos) ? "Sim" : "Não").append("\n");

                Text analiseText = new Text(analise.toString());
                analiseText.setFont(new Font("Arial", 14));
                analiseText.setFill(Color.web("#ACACAC"));

                graphPane2.getChildren().add(analiseText);
                graphPane2.setStyle("-fx-padding: 10; -fx-alignment: center-left; -fx-background-color: #363636; -fx-border-color: WHITE");
            }
        }
    }

    //para matriz
    public static void leitor(String nome, boolean isMatriz) {
        if(isMatriz){
            Matriz matriz = new Matriz();
            lerArquivo(nome);
            matriz.analisarGrafo(matrizAdjacencia);
        }else{
            Lista lista = new Lista();
            lerArquivo(nome);
            lista.analisarGrafoL(listaAdjacencia);
        }

    }


    private static void lerArquivo(String nome) {
        String caminhoArquivo = Paths.get("src/main/java/com/example/grafo/arquivosTxt", nome + ".txt").toString();
        System.out.println(caminhoArquivo);

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = reader.readLine();
            if (linha != null) {
                rotulos = linha.split(",");
                int tamanho = rotulos.length;

                matrizAdjacencia = new int[tamanho][tamanho];
                listaAdjacencia = new Lista[tamanho];

                for (int i = 0; i < tamanho; i++) {
                    listaAdjacencia[i] = new Lista();
                }

                int i = 0;
                while ((linha = reader.readLine()) != null) {
                    String[] valores = linha.split(",");
                    for (int j = 0; j < valores.length; j++) {
                        matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
                        if (matrizAdjacencia[i][j] != 0) {
                            String aresta = rotulos[j];
                            listaAdjacencia[i].inserirFim(aresta, matrizAdjacencia[i][j]);
                        }
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        select.setValue("Matriz"); // Define "Matriz" como o valor padrão
    }
}
