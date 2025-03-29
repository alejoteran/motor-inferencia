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

        // SE VERFICIA CON LA BASE DE CONOCIMIENTO
        for(Regla r : reglas){
            for(Predicado pred : r.getPredicados()){
                if(pred.getNombre().equals(p.getNombre()) && !pred.getNegado() == p.getNegado()){
                    // como el nombre del predicado es el mismo y sus negados son diferentes se niegan
                    // y se tiene que crear la nueva clausula con lo que sobra de las reglas
                }
            }
        }
        return true;
    }
}
