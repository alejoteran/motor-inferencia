import java.util.ArrayList;

public class Motor {

    private ArrayList<Regla> reglas = new ArrayList<Regla>();

    public Motor() {
    }

    public Motor(ArrayList<Regla> reglas) {
        this.reglas = reglas;
    }

    public ArrayList<Regla> getReglas() {
        return reglas;
    }

    public void setReglas(ArrayList<Regla> reglas) {
        this.reglas = reglas;
    }

    public void add_regla(Regla regla){
        reglas.add(regla);
    }


    public ArrayList<String> mostrar_reglas() {
        ArrayList<String> mostrar = new ArrayList<String>();
        for(Regla regla : reglas){
            mostrar.add(regla.toString());
        }
        return mostrar;
    }

    // Metodo para probar si una cláusula es verdadera o falsa
    public boolean probar(Predicado p) {
        System.out.println("Intentando probar la cláusula: " + p);
        // Lista de cláusulas por resolver, inicializada con las reglas y la cláusula negada
        ArrayList<Regla> clausulasPorResolver = new ArrayList<>(reglas);
        ArrayList<Predicado> predicados = new ArrayList<>();
        predicados.add(p);
        clausulasPorResolver.addFirst(new Regla(predicados));
        System.out.println("Cláusulas por resolver: " + clausulasPorResolver);
        // Mientras haya cláusulas por resolver
        while (!clausulasPorResolver.isEmpty()) {
            // Tomar la primera cláusula de la lista
            Regla clausulaActual = clausulasPorResolver.removeFirst();
            System.out.println("Evaluando cláusula: " + clausulaActual);

            // Intentar resolver la cláusula actual con cada regla
            for (Regla regla : reglas) {
                System.out.println("Comparandola con "+regla.toString());
                Regla resolvente = resolver(clausulaActual, regla);
                if (resolvente != null) {
                    System.out.println("Resultado de la resolución: " + resolvente);
                    // Si se produce la cláusula nula, la sentencia es verdadera
                    if (resolvente.getPredicados().isEmpty()) {
                        System.out.println("Se produjo la cláusula nula. La sentencia es verdadera.");
                        return true;
                    }
                    // Añadir el resultado de la resolución a la lista de cláusulas por resolver
                    clausulasPorResolver.add(resolvente);
                }
            }
            //para probar con solo una iteracion quitar despues
            clausulasPorResolver.clear();
        }
        // Si no se produce la cláusula nula, la sentencia es falsa
        System.out.println("No se produjo la cláusula nula. La sentencia es falsa.");
        return false;
    }

    // Metodo para resolver dos reglas y obtener una nueva regla resolvente
    private Regla resolver(Regla r1, Regla r2) {
        ArrayList<Predicado> lista1 = new ArrayList<>(r1.getPredicados());
        ArrayList<Predicado> lista2 = new ArrayList<>(r2.getPredicados());

        System.out.println("Resolviendo reglas:");
        System.out.println("Regla 1: " + r1);
        System.out.println("Regla 2: " + r2);

        boolean huboCambios;
        do {
            huboCambios = false;
            for (int i = 0; i < lista1.size() && !huboCambios; i++) {
                for (int j = 0; j < lista2.size() && !huboCambios; j++) {
                    Predicado p1 = lista1.get(i);
                    Predicado p2 = lista2.get(j);
                    if (p1.getNombre().equals(p2.getNombre()) && p1.getNegado() != p2.getNegado()) {
                        System.out.println("Contradicción encontrada: " + p1 + " y " + p2);
                        lista1.remove(i);
                        lista2.remove(j);
                        huboCambios = true;
                    }
                }
            }
        } while (huboCambios);

        ArrayList<Predicado> resultado = new ArrayList<>();
        resultado.addAll(lista1);
        resultado.addAll(lista2);

        System.out.println("Resultado de la resolución: " + resultado);

        return resultado.isEmpty() ? null : new Regla(resultado);
    }
}
