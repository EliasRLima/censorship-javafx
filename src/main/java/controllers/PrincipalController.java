package controllers;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.ArquivoService;
import util.CarregadorRecursos;
import util.ColorUtils;
import util.Templates;

public class PrincipalController implements Initializable{
	
	@FXML
	ImageView img_atual, img_edit;
	
	@FXML
	JFXButton btn_open, btn_edit;
	
	private ArquivoService arqs;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		arqs = ArquivoService.getInstance();
		
		btn_open.setOnAction(e -> {
			arqs.abrir();
			
			if(arqs.carregado()) {
				try {
					BufferedImage bfImage = ImageIO.read(arqs.getArquivo());
					Image image = SwingFXUtils.toFXImage(bfImage, null);
	            	img_atual.setImage(image);
	            	img_edit.setImage(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btn_edit.setOnAction(e -> {
			try {
				if(arqs.carregado()) {
					BufferedImage bfImage = ImageIO.read(arqs.getArquivo());
					censurarPossivelSangue(bfImage);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public boolean censurarPossivelSangue(BufferedImage img) {
		 try {
	            WritableRaster r = img.getRaster();
	            int total = 0, red = 0;
	            
	            int pixels[] = new int[img.getWidth() * img.getHeight()];
	            for (int x = 0; x < img.getWidth(); x++) {
	                for (int y = 0; y < img.getHeight(); y++) {
	                    r.getPixel(x, y, pixels);
	                    total++;
	                    //System.out.println(ColorUtils.humanColors[ColorUtils.getHumanColor(pixels[0], pixels[1], pixels[2])[3]]);
	                   if(ColorUtils.humanColors[ColorUtils.getHumanColor(pixels[0], pixels[1], pixels[2])[3]].equals("vermelho")) {
	                	   red++;
	                    	
	                	   pixels[0] = 94;
	                       pixels[1] = 94;
	                       pixels[2] = 94;
	                        
	                       r.setPixel(x, y, pixels);
	                    }
	                }
	            }
	            
	           // System.out.println("total: "+total+"\nvermelho: "+red+"\npct: "+(double)((double)red/total));
	            
	            if(arqs.carregado()) {
	            	File arq = arqs.getArquivo();
		            if((double)((double)red/total) > 0.1 || (red > 1000 && total > 10000 && (double)((double)red/total) > 0.01)) {
		            	int radius = 15;
	        		    int size = radius * 2 + 1;
	        		    float weight = 1.0f / (size * size);
	        		    float[] data = new float[size * size];

	        		    for (int i = 0; i < data.length; i++) {
	        		        data[i] = weight;
	        		    }

	        		    Kernel kernel = new Kernel(size, size, data);
	        		    BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	        		    
	        			BufferedImage bfImage2 = null;
						BufferedImage blurredImage = op.filter(img, bfImage2);
						Image image = SwingFXUtils.toFXImage(blurredImage, null);
						img_edit.setImage(image);
		            }else {
		            	img = ImageIO.read(arqs.getArquivo());
		            	Image image = SwingFXUtils.toFXImage(img, null);
		            	img_edit.setImage(image);
		            }
	            }

	            return true;
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
	}

}
