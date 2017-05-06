package TD1;

import java.util.ArrayList;

public class FileAttente {

   //attributs
   private int taille_file;
   private ArrayList<Client> file_attente = new ArrayList<>();

   public FileAttente(int taille_file) {
      this.taille_file = taille_file;
   }
   
   public boolean estVide() {
      return file_attente.isEmpty();
   }

   public boolean estPremier(Client client) {      
      return file_attente.indexOf(client)== 0 ;
   }
      
   synchronized public boolean entrer(Client client) {
      if(file_attente.size() < taille_file){
         file_attente.add(client);
         return true;
      }

      return false;
   }
   
  synchronized public boolean sortir(Client client) {
      if(estPremier(client)){
         file_attente.remove(client);
         notifyAll();
         return true;
      }

      return false;
   }
}
