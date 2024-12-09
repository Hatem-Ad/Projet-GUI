import java.util.ArrayList;
import java.util.Scanner;

public class ForumApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> messages = new ArrayList<>();

        System.out.println("Bienvenue dans le forum !");

        while (true) {
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1. Écrire un message");
            System.out.println("2. Voir tous les messages");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrez votre message : ");
                    String message = scanner.nextLine();
                    messages.add(message);
                    System.out.println("Message ajouté !");
                    break;

                case 2:
                    System.out.println("\nMessages du forum :");
                    if (messages.isEmpty()) {
                        System.out.println("Aucun message pour l'instant.");
                    } else {
                        for (int i = 0; i < messages.size(); i++) {
                            System.out.println((i + 1) + ". " + messages.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("Merci d'avoir utilisé le forum. À bientôt !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
}
