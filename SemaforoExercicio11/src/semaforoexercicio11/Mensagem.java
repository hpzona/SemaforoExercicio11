package semaforoexercicio11;

import java.util.concurrent.Semaphore;

public class Mensagem {

   private static final int LIMITE_MENSAGENS = 5; // máximo 5 mensagens
   private Object[] caixa;
   private int caixaEntra, caixaSai;
   private Semaphore slotsVazios;
   private Semaphore slotsOcupados;
   private boolean caixaCheia;

   public Mensagem() {
      caixaEntra = 0;
      caixaSai = 0;
      caixa = new Object[LIMITE_MENSAGENS];
      slotsVazios = new Semaphore(LIMITE_MENSAGENS);
      slotsOcupados = new Semaphore(0);
      caixaCheia = false;
   }

   public void receberMensagens(Object item) throws InterruptedException {
      if (!isCaixaCheia()) {
         slotsVazios.acquire();
         caixa[caixaEntra] = item;
         caixaEntra = (caixaEntra + 1) % LIMITE_MENSAGENS;
         if (caixaEntra == 0) {
            caixaEntra = LIMITE_MENSAGENS;
         }
      } else {
         slotsOcupados.release();
      }
   }

   public Object notificarUsuario() throws InterruptedException {
      Object item = null;
      slotsOcupados.acquire();

      if (isCaixaCheia()) {
         System.out.print("5 mensagens - Enviadas por: ");
         for (int i = 0; i < LIMITE_MENSAGENS; i++) {
            item = caixa[caixaSai];
            System.out.print(item + " ");
            caixaSai = (caixaSai + 1) % LIMITE_MENSAGENS;
            if (caixaSai == 0) {
               caixaSai = LIMITE_MENSAGENS;
            }

            slotsVazios.release();
         }
         System.out.println();
         caixaEntra = 0;
         caixaSai = 0;
         caixaCheia = false;
      }
      System.out.println();
      return item;
   }

   public boolean isCaixaCheia() {
      return caixaCheia;
   }

   public void setCaixaCheia(boolean caixaCheia) {
      this.caixaCheia = caixaCheia;
   }
}
