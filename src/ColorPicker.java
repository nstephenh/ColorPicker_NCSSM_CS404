import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by nsh on 1/19/16.
 */
public class ColorPicker extends Application{

    private Canvas canvas;

    public void init(){
        canvas = new Canvas(500, 500);
    }

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primary){
        primary.setTitle("Color Picker");

        GridPane maingrid = new GridPane();

        maingrid.add(new Label("Red"), 1, 1);
        maingrid.add(new Label("Green"), 2, 1);
        maingrid.add(new Label("Blue"), 3, 1);


        maingrid.add(new ColorSlider("Red"), 1, 2);
        maingrid.add(new ColorSlider("Green"), 2, 2);
        maingrid.add(new ColorSlider("Blue"), 3, 2);



        VBox mainvert = new VBox();
        mainvert.getChildren().addAll(maingrid, canvas);

        Scene s = new Scene(mainvert, Color.WHITE);
        primary.setScene(s);
        primary.show();



    }
    class ColorSlider extends Slider{
        public ColorSlider(String colorName){
            setMin(0);
            setMax(255);
            setShowTickMarks(true);
            setShowTickLabels(true);

        }
    }
}
