package Clases;

public class Dado {
    
    public int valor;
    public boolean fueElegido;
    
    public Dado(int valor, boolean fueElegido) {
        this.valor = valor;
        this.fueElegido = fueElegido;
    }
    
    public void setValor(int nuevoValor){
        this.valor=nuevoValor;
    }
    public void setFueElegido(boolean valor){
        this.fueElegido=valor;
    }
    public int getValor(){
        return this.valor;
    }
    public boolean fueElegido(){
        return this.fueElegido;
    }
}
