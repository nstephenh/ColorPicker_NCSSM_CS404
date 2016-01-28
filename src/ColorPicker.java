import com.sun.xml.internal.ws.api.model.ExceptionType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by nsh on 1/19/16.
 */
public class ColorPicker extends Application{

    private Canvas canvas;

    String colorBlue = "00";
    String colorRed = "00";
    String colorGreen = "00";

    HexColorBox hexValueBox = new HexColorBox();

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

        ColorSlider redSlider = new ColorSlider();
        redSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorRed = Integer.toHexString((int) redSlider.getValue());
            repaint();
        });
        ColorSlider greenSlider = new ColorSlider();
        greenSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorGreen = Integer.toHexString((int) greenSlider.getValue());
            repaint();
        });
        ColorSlider blueSlider = new ColorSlider();
        blueSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorBlue = Integer.toHexString((int) blueSlider.getValue());
            repaint();
        });
        maingrid.add(redSlider, 1, 2);
        maingrid.add(greenSlider, 2, 2);
        maingrid.add(blueSlider, 3, 2);
        maingrid.add(hexValueBox, 4, 2);



        BorderPane mainvert = new BorderPane();
        mainvert.setTop(maingrid);
        mainvert.setCenter(canvas);

        Scene s = new Scene(mainvert, javafx.scene.paint.Color.WHITE);
        primary.setScene(s);
        primary.show();



    }
    public void repaint(){

        String paintmethis = "0x" + colorRed + colorGreen + colorBlue;
        hexValueBox.setText(paintmethis);
        System.out.println(paintmethis);
        GraphicsContext frenchgirl = canvas.getGraphicsContext2D();
        frenchgirl.setFill(Color.web(paintmethis));
        frenchgirl.fillRect(0,0,canvas.getHeight(), canvas.getWidth());


    }
    class ColorSlider extends Slider{
        public ColorSlider(){
            setMin(0);
            setMax(255);
            setShowTickMarks(true);
            setShowTickLabels(true);

        }
    }

    class HexColorBox extends TextField {
        public HexColorBox(){
            setText("0x000000");
            setOnKeyTyped(event ->{
                try{
                    colorRed = getText(2, 4);
                    colorGreen = getText(4, 6);
                    colorBlue = getText(6, 8);
                    repaint();
                }catch(IndexOutOfBoundsException e){

                }
            });
        }

    }
}
