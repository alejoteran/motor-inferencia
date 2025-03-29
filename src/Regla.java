import java.util.ArrayList;

public class Regla {

    // TODOS ESTAN UNIDOS POR UN OR v
    private ArrayList<Predicado> predicados = new ArrayList<Predicado>();

    public Regla() {
    }

    public Regla(ArrayList<Predicado> predicados) {
        this.predicados = predicados;
    }

    public ArrayList<Predicado> getPredicados() {
        return predicados;
    }

    public void setPredicados(ArrayList<Predicado> predicados) {
        this.predicados = predicados;
    }

    public void add_predicado(Predicado predicado){
        predicados.add(predicado);
    }

    @Override
    public String toString() {
        ArrayList<String> lista_predicados = new ArrayList<String>();
        for (Predicado p : predicados){
            lista_predicados.add(p.toString());
        }
        String regla = String.join(" v ", lista_predicados);
        return  "Regla { " + regla + " }";
    }
}
