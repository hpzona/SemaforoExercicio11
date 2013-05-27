package semaforoexercicio11;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Usuario implements Runnable {

   private Mensagem msg;

   public Usuario(Mensagem msg) {
      this.msg = msg;
   }

   public void run() {
      while (true) {
       
          try {
            msg.notificarUsuario();
            Thread.sleep(100);
         } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
