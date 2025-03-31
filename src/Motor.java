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
        ArrayList<Regla> clausulasPorResolver = new ArrayList<>();
        clausulasPorResolver.add(new Regla(new ArrayList<Predicado>(){{ add(p); }}));

        while (!clausulasPorResolver.isEmpty()) {
            Regla clausulaActual = clausulasPorResolver.remove(0);
            boolean reglaEncontrada = false;

            for (Predicado predActual : clausulaActual.getPredicados()) {
                for (Regla unaRegla : reglas) {
                    // Se verifica si esta regla contradice el predicado actual
                    for (Predicado predRegla : unaRegla.getPredicados()) {
                        if (predActual.getNombre().equals(predRegla.getNombre())
                                && predActual.getNegado() != predRegla.getNegado()) {
                            // Resuelve y verifica si se produce cláusula nula
                            Regla resolvente = resolver(clausulaActual, unaRegla);
                            if (resolvente == null) {
                                System.out.println("Se produjo la cláusula nula.");
                                return true;
                            }
                            clausulasPorResolver.add(resolvente);
                            reglaEncontrada = true;
                            break;
                        }
                    }
                    if (reglaEncontrada) break;
                }
                if (reglaEncontrada) break;
            }

            // Si no encontró regla que contradiga ningún predicado, termina
            if (!reglaEncontrada) {
                System.out.println("No se encontro regla que aplique, por lo que no se produjo la cláusula nula. La sentencia es falsa.");
                return false;
            }
        }

        System.out.println("No se produjo la cláusula nula. La sentencia es falsa.");
        return false;
    }

    // Metodo para resolver dos reglas y obtener una nueva regla resolvente
    private Regla resolver(Regla r1, Regla r2) {
        ArrayList<Predicado> lista1 = new ArrayList<>(r1.getPredicados());
        ArrayList<Predicado> lista2 = new ArrayList<>(r2.getPredicados());

        System.out.println("Resolviendo: " + r1 + " y " + r2);

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
        System.out.println("Resultado: " + resultado);

        return resultado.isEmpty() ? null : new Regla(resultado);
    }
}
