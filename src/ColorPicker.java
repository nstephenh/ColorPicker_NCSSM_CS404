import com.sun.xml.internal.ws.api.model.ExceptionType;
import javafx.application.Application;
import javafx.geometry.Pos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by nsh on 1/19/16.
 */
public class ColorPicker extends Application{

    private Canvas canvas;

    //These three strings store our colors.
    String colorBlue = "00";
    String colorRed = "00";
    String colorGreen = "00";

    HexColorBox hexValueBox = new HexColorBox();
    ColorSlider redSlider = new ColorSlider();
    ColorSlider greenSlider = new ColorSlider();
    ColorSlider blueSlider = new ColorSlider();




    public void init(){
        canvas = new Canvas(800, 800);

    }

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primary){
        primary.setTitle("Color Picker");

        GridPane maingrid = new GridPane();

        maingrid.add(new NiceLabel("Red","0xFF0000"), 1, 1);
        maingrid.add(new NiceLabel("Green","0x00FF00"), 2, 1);
        maingrid.add(new NiceLabel("Blue","0x0000FF"), 3, 1);
        maingrid.add(new NiceLabel("Hex Code","0x000000"), 4, 1);


        redSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorRed = Integer.toHexString((int) redSlider.getValue());
            repaint();
        });

        greenSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorGreen = Integer.toHexString((int) greenSlider.getValue());
            repaint();
        });

        blueSlider.valueProperty().addListener( (ov, old_val, new_val) ->{
            colorBlue = Integer.toHexString((int) blueSlider.getValue());
            repaint();
        });
        maingrid.add(redSlider, 1, 2);
        maingrid.add(greenSlider, 2, 2);
        maingrid.add(blueSlider, 3, 2);
        maingrid.add(hexValueBox, 4, 2);
        maingrid.setAlignment(Pos.CENTER);
        maingrid.setHgap(10);





        BorderPane mainvert = new BorderPane();
        mainvert.setTop(maingrid);
        mainvert.setCenter(canvas);


        Scene s = new Scene(mainvert, javafx.scene.paint.Color.WHITE);
        primary.setScene(s);
        primary.show();



    }
    public void repaint(){
        //Make sure we've got the right number of digits
        if (colorBlue.length() < 2){
            colorBlue = "0" + colorBlue;
        }
        if (colorRed.length() < 2){
            colorRed = "0" + colorRed;
        }
        if (colorGreen.length() < 2){
            colorGreen = "0" + colorGreen;
        }

        //Update Sliders
        redSlider.setValue(Integer.parseInt(colorRed, 16));
        blueSlider.setValue(Integer.parseInt(colorBlue, 16));
        greenSlider.setValue(Integer.parseInt(colorGreen, 16));
        //Update Textbox
        String paintmethis = "0x" + colorRed.toUpperCase() + colorGreen.toUpperCase() + colorBlue.toUpperCase();
        hexValueBox.setText(paintmethis);

        //Update canvas
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

    class NiceLabel extends Label{
        public NiceLabel(String Text, String hexColor){
            setTextAlignment(TextAlignment.CENTER);
            setTextFill(Color.web(hexColor));
            setText(Text);
            setAlignment(Pos.CENTER);
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
