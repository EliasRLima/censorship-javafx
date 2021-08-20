package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;

public class ArquivoService {

	private File arquivo;
	private final FileChooser fileChooser;
	private static ArquivoService instance;

	private ArquivoService() {
		this.fileChooser = new FileChooser();
	}

	public static ArquivoService getInstance() {
		if (instance == null)
			instance = new ArquivoService();
		return instance;
	}

	public boolean abrir() {		
		fileChooser.setTitle("Abrir arquivo");
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			arquivo = file;
			return true;
		}
		return false;
	}
	
	public boolean carregado() {
		return this.arquivo != null;
	}
	
	public File getArquivo() {
		return this.arquivo;
	}
}
