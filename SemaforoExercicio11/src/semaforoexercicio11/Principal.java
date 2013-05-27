package semaforoexercicio11;

public class Principal {

   public static void main(String[] args) {
      Mensagem msg = new Mensagem();
      Thread emissora = new Thread(new Emissora(msg));
      Thread usuario = new Thread(new Usuario(msg));
      
      
      emissora.start();
      usuario.start();
      
   }
}
