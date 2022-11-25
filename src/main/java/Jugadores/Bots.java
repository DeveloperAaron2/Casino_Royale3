package Jugadores;

import Escenario.Casino;

public class Bots extends Thread{
    private int dinero;
    private boolean resultadoUltimaApuesta;

    int numeroJugador;
    int apuestaPredeterminada;
    boolean aposte = false;


    private Casino buffer;
    public Bots(int numeroJugador){
        this.numeroJugador = numeroJugador;
        this.dinero = 500;
        this.apuestaPredeterminada = 20;
    }

    public void setBuffer(Casino buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (dinero>0) {
                int apuestaAleatoria = (int) (Math.random() * 8);
                String apuesta = buffer.getTipoDeApuesta()[apuestaAleatoria];
                EsperarUnSegundo();
                Apostar(resultadoUltimaApuesta,apuesta);
                aposte = true;
        }
        buffer.sumaUnoMenos(1);
        System.out.println("jugador " + numeroJugador + " ha muerto");
    }

    public boolean isAposte() {
        return aposte;
    }

    private synchronized void Apostar(boolean resultadoUltimaApuesta, String apuesta) {
        if (resultadoUltimaApuesta) {
            buffer.apostar(300, apuesta, numeroJugador);
        } else
            buffer.apostar(300, apuesta, numeroJugador);
    }

    private void EsperarUnSegundo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void setResultadoUltimaApuesta(boolean resultadoUltimaApuesta) {
        if(!resultadoUltimaApuesta)
            apuestaPredeterminada = 20;
        this.resultadoUltimaApuesta = resultadoUltimaApuesta;
    }
    public int getDinero() {
        return dinero;
    }

    public void setAposte(boolean aposte) {
        this.aposte = aposte;
    }
}
