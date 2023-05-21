package sieteymediaFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author profesorado
 */
public class SieteYMediaAPP extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SieteYMediaFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JUEGO DE CARTAS - SIETE Y MEDIA");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:.//src//imagenes//sieteYMedia.png"));
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
    }
    
}
