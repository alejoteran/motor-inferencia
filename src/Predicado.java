import java.util.ArrayList;

public class Predicado {
    private boolean negado;
    private String nombre;
    //private ArrayList<String> variables = new ArrayList<String>();


    public Predicado() {
    }

    public Predicado(Boolean negado, String nombre, ArrayList<String> variables) {
        this.negado = negado;
        this.nombre = nombre;
        //this.variables = variables;
    }

    public Boolean getNegado() {
        return negado;
    }

    public void setNegado(Boolean negado) {
        this.negado = negado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //public ArrayList<String> getVariables() {
        //return variables;
    //}

    //public void setVariables(ArrayList<String> variables) {
        //this.variables = variables;
    //}


    @Override
    public String toString() {
        if(negado){
            return "-" + nombre;
        }
        return nombre;
    }
}
