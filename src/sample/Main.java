package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private Label[][] labels;
    private int[][] labelsValue;
    private Button refreshBtn;
    private Button findLargestBlockBtn;

    @Override
    public void start(Stage primaryStage) throws Exception{
  //      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Exercise22_19");
        refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i=0; i<10; i++){
                    for(int j=0; j<10 ;j++){
                        labelsValue[i][j] = new Random().nextInt(2);
                        labels[i][j].setText("  "+labelsValue[i][j]+"");
                        labels[i][j].setBackground(new Background(new BackgroundFill(new Color(1,1,1,0),null,null)));
                    }
                }
            }
        });

        findLargestBlockBtn = new Button("Find Largest Block");
        findLargestBlockBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int max = 1;
                int maxIndexOfX = 0;
                int maxIndexOfY = 0;
                for(int i=1; i<10; i++){
                    for(int j=1; j<10; j++){
                        if(labelsValue[i][j] != 0){
                            int k=1;
                            boolean flag = true;
                            while(flag) {
                                labelsValue[i][j] = k;
                                if(i+k<10 && j+k<10){
                                    for (int y=j; y<10 && y<=j+k; y++){
                                        if(labelsValue[i+k][y] == 0){
                                            flag = false;
                                        }
                                    }
                                    for (int x=i; x<10 && x<=i+k; x++){
                                        if(labelsValue[x][j+k] == 0){
                                            flag = false;
                                        }
                                    }
                                }
                                else{
                                    flag = false;
                                }
                                k++;
                            }
                        }
                        if(labelsValue[i][j] >= max){
                            max = labelsValue[i][j];
                            maxIndexOfX = i;
                            maxIndexOfY = j;
                        }
                        //labels[i][j].setText(labelsValue[i][j]+"");
                    }
                }

                for(int i=maxIndexOfX; i<maxIndexOfX+max; i++){
                    for(int j=maxIndexOfY; j<maxIndexOfY+max; j++){
                        labels[i][j].setBackground(new Background(new BackgroundFill(new Color(0,0,0,1),null,null)));
                    }
                }
            }
        });

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10,10,10,10));
        labels = new Label[10][10];
        labelsValue = new int[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10 ;j++){
                root.add(labels[i][j] = new Label(), j, i);
                labels[i][j].setStyle("-fx-border-color: black");
                labels[i][j].setPrefSize(40,40);
                labelsValue[i][j] = new Random().nextInt(2);
                labels[i][j].setText("  "+labelsValue[i][j]+"");
            }
        }
        root.add(refreshBtn, 4, 10, 2, 1);
        root.add(findLargestBlockBtn, 6, 10, 4, 1);


        primaryStage.setScene(new Scene(root, 400, 440));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
