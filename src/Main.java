import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Motor motor = new Motor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el numero de reglas de la base de conocimiento");
        int iter = scanner.nextInt();

        for(int i=0; i<iter; i++){

            Regla regla = new Regla();

            System.out.println("Ingrese la regla " + (i+1));
            String regla_string = scanner.next();
            String[] partes = regla_string.split("v");

            for(int j=0 ; j< partes.length; j++){
                Predicado p = new Predicado();
                p.setNegado(partes[j].contains("-"));
                partes[j] = partes[j].replace("-", "");
                p.setNombre(partes[j]);
                regla.add_predicado(p);
            }
            motor.add_regla(regla);
        }
        for(String regla_str : motor.mostrar_reglas()){
            System.out.println(regla_str);
        }

        System.out.println("Ingrese la clausula a probar");
        String clausula = scanner.next();

        // SE CREA UN PREDICADO CON EL STRING DE LA REGLA Y SE NIEGA
        Predicado p = new Predicado();
        p.setNegado(!clausula.contains("-"));
        clausula = clausula.replace("-", "");
        p.setNombre(clausula);

        if(motor.probar(p)){
            System.out.println("La clausula es verdadera");
        }else{
            System.out.println("La clausula es falsa");
        }
    }
}