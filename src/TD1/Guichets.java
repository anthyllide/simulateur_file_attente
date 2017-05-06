package TD1;

import java.util.HashMap;
import java.util.Set;

public class Guichets {

   //attributs
   private HashMap<Integer, Client> guichets = new HashMap<>();

   public Guichets(int nb_guichets) {
         for(int i=0; i<nb_guichets; i++){
            guichets.put(i,null);//guichet vide
         }
   }
   
   synchronized public int entrer(Client client) {
      Set<Integer> keys= guichets.keySet();
      //on regarde tous les guichets, si un est libre on retourne son indice
      for(Integer key : keys){
         if(guichets.get(key) == null){
            guichets.put(key, client);
            return key;
         }
      }
      return -1; // sinon on retourne -1
   }
   
  synchronized public boolean quitter(Client client, int guichet) {
      if(guichets.get(guichet) == client){
         guichets.put(guichet,null);
         notifyAll();
         return true;
      }
      return false;
   }
}
