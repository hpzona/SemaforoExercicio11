package semaforoexercicio11;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Emissora implements Runnable {

    private Mensagem msg;
    Random num;

    public Emissora(Mensagem msg) {
        this.msg = msg;
        num = new Random();
    }

    public void run() {
        String numero = "";
        int i = 0;
        
        while (true) {

            try {
                for (int n = 0; n < 4; n++) {
                   numero = numero + num.nextInt(10);
                }
                msg.receberMensagens(numero);
                numero = "";
                i++;
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Emissora.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (i == 5) {
                i=-1;
                msg.setCaixaCheia(true);
            }
        }
    }
}
