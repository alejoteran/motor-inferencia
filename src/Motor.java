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

    public boolean probar(Predicado p) {
        System.out.println("Intentando probar la cláusula: " + p);
        for(Regla r : reglas){
            System.out.println("Evaluando regla: " + r);
            for(Predicado pred : r.getPredicados()){
                if(pred.getNombre().equals(p.getNombre()) && pred.getNegado() != p.getNegado()){
                    System.out.println("Se encontró una contradicción con el predicado: " + pred);
                    return true;
                }
            }
        }
        return false;
    }
}
