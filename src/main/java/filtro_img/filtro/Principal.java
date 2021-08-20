package filtro_img.filtro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.CarregadorRecursos;
import util.Templates;

public class Principal extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(CarregadorRecursos.getResource(Templates.BASE.getUrl()));
		  Scene scene = new Scene(root, 1024, 768); // resolucao inicial
		  primaryStage.setScene(scene);
		  primaryStage.setTitle("");
		  primaryStage.setMinHeight(768);
		  primaryStage.setMinWidth(1024);
		  primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
