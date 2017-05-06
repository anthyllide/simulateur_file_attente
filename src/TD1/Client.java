package TD1;

public class Client extends Thread {

   //attributs clients
   private int numero_client;
   private int etat = 0;

   private static Guichets guichets = new Guichets(2);
   private static FileAttente file = new FileAttente(3);

   //constructeur
   public Client(int numero_client) {
      this.numero_client = numero_client;
   }

   public void run() {
      boolean fin = false;

      while (!fin) {
         //soit il y a de la place  dans la file d'attente et état = nouveau (ici 0)
         if (file.entrer(this) && etat == 0) {
            //il rentre dans la file d'attente
            // il est en attente (état = 1)
            etat = 1;
            System.out.println("Le client numéro " + numero_client + " entre dans la file d'attente et patiente sagement !");

            //s'il est premier, il regarde si un guichet est libre
            if (etat == 1 && file.estPremier(this)) {

               System.out.println("Le client numéro " + numero_client + " est premier et il regarde si un guichet est libre.");
               int numero_guichet = guichets.entrer(this);

               //si un guichet est libre
               if (numero_guichet != -1) {  //il passe au guichet
                     TraitementGuichet(fin,numero_guichet);
                  //si tous les guichets sont pris, le client attend qu'un guichet soit libre
               } else {
                  synchronized (guichets) {
                  try {
                        guichets.wait();
                     } catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }
               }
               //il n'est pas premier et attend
            } else if (etat == 1 && !file.estPremier(this)) {
               synchronized (file) {
                  try {
                     System.out.println("Le client numéro " + numero_client + " attend d'être premier!");
                     file.wait();
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
            // si la file d'attente est pleine, le client fait demi-tour
         }else if (!file.entrer(this) && etat == 0) {
               file.sortir(this);
               System.out.println("le client numéro " + numero_client + " fait demi-tour ... TROP DE MONDE !!");
               fin = true;
         }
      }
   }

   private void TraitementGuichet(boolean fin,int numero_guichet){
      try {
         System.out.println("Le client numéro " + numero_client + " est au guichet n°" + numero_guichet);
         file.sortir(this);//il sort de la file d'attente
         this.sleep(2000 + (int) (Math.random() * 1000));// il attend au guichet
         guichets.quitter(this, numero_guichet); //une fois terminé,il part
         System.out.println("Le client numéro " + numero_client + " part du guichet " + numero_guichet + " !");
         fin = true;// on arrête la boucle
      } catch (InterruptedException e) {
        e.printStackTrace();
        }

   }
}

