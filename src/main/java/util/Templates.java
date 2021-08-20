package util;

public enum Templates {
  
  BASE("view/tela_inicio.fxml"),
  IMAGE("image/flor.jpg"),
  IMAGE2("image/flor2.jpg");
  
  private String url;
  private Templates(String url){
    this.url = url;
  }
  public String getUrl() {
    return this.url;
  }
  
}
