import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import User.User;

public class Main {
    User user;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Run Or Fight");

            while (true) {
                System.out.println("1. Iniciar Sesion");
                System.out.println("2. Registrarse");
                System.out.println("3. RUN");
                System.out.print("Seleccione una opción: ");
                String opcion = br.readLine();

                switch (opcion) {
                    case "1":
                        login();
                        break;
                    case "2":
                        signUp();
                        break;
                    case "3":
                        System.out.println("¡RUN!");
                        return;
                    default:
                        System.out.println("Elige una opcion valida");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nombre de usuario:");
        String username = br.readLine();
        System.out.println("Password:");
        String password = br.readLine();

        BufferedReader reader = new BufferedReader(new FileReader("user.txt"));
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                found = true;
                break;
            }
        }
        reader.close();

        if (found) {
            System.out.println("¡Iniciando sesion!");
        } else {
            System.out.println("Usuario o password incorrectos.");
        }
    }

    public static void signUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese un nombre de usuario:");
        String username = br.readLine();
        System.out.println("Ingrese un password:");
        String password = br.readLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true));
        writer.write(username + "," + password + "\n");
        writer.close();

        System.out.println("signUp exitoso");
    }
}
