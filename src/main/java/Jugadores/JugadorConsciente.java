package Jugadores;

import Escenario.Casino;

import java.util.Scanner;

public class JugadorConsciente extends Thread{
    private int dinero;
    private boolean resultadoUltimaApuesta;

    int numeroJugador;
    int apuestaPredeterminada;
    boolean aposte = false;

    private Casino buffer;
    public JugadorConsciente(int numeroJugador){
        this.numeroJugador = numeroJugador;
        this.dinero = 500;
        this.apuestaPredeterminada = 20;
    }

    public void setBuffer(Casino buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (dinero>0) {
            //EsperarUnSegundo();
            System.out.println("introduce tu tipo de apuesta (numero) y la cantidad separados por comas");
            String [] valores = sc.nextLine().split(",");
            int tipoApuesta = Integer.parseInt(valores[0]);
            int dineroapostado = Integer.parseInt(valores[1]);
            buffer.setJugadorConscienteTermina(true);
            String apuesta = buffer.getTipoDeApuesta()[tipoApuesta];
            buffer.apostar(dineroapostado,apuesta,numeroJugador);
            aposte = true;
        }
        sc.close();
        buffer.sumaUnoMenos(1);
    }


    public boolean isAposte() {
        return aposte;
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

    public synchronized void setAposte(boolean aposte) {
        this.aposte = aposte;
    }
}

