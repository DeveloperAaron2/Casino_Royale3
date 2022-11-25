package Jugadores;

import Escenario.Casino;

public class Croupier extends Thread{
    public Casino buffer;
    private int numeroGenerado;

    @Override
    public void run() {
        while(!buffer.isJuegoFinalizado()){
            numeroGenerado =(int) (Math.random() * 37);
            buffer.empezar = false;
            try {
                CuentaAtrasNuevaRonda();
               //EsperarAlJugadorConsciente();
                buffer.setEmpezar(true);
                Thread.sleep(3000);
                //buffer.SetTurnoJugadorConsciente();
                CuentraAtrasApuestas();
                buffer.AsignarTurnos();
                CuentaAtrasApuesten();
                buffer.setEmpezar(false);
                CerramosYSiguiente();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
         }
    }

    private void CuentraAtrasApuestas() {
        try {

            Thread.sleep(1000);
            System.out.println("3...");
            Thread.sleep(1000);
            System.out.println("2...");
            Thread.sleep(1000);
            System.out.println("1...");
            Thread.sleep(1000);
            System.out.println("Apuesten");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void EsperarAlJugadorConsciente() throws InterruptedException {
        while (!buffer.isJugadorConscienteTermina()){
            wait();
        }
    }

    private static void CuentaAtrasNuevaRonda() throws InterruptedException {
        System.out.println("Los tipos de apuesta son: " +
                "\n 1, Apuesta al rojo (x2 apostado)," +
                "\n 2, Apuesta al negro (x2 apostado)" +
                "\n 3 Apuesta a un numero Par (x2 apostado)," +
                "\n 4 Apuesta a un numero impar (x2 apostado)," +
                "\n 5 Apuesta un numero del 1 al 12 (x3 apostado)," +
                "\n 6 Apuesta un numero del 13 al 24 (x3 apostado)," +
                "\n 7 Apuesta un numero del 25 al 36 (x3 apostado)," +
                "\n 1 Apuesta a un numero especifico(x20 apostado),");
        System.out.println("Tienes un par de segundos para pensartelo");

    }

    private static void CerramosYSiguiente() throws InterruptedException {
        System.out.println("Cerramos apuestas");
        Thread.sleep(4000);
        System.out.println("Siguiente ronda");
    }

    private static void CuentaAtrasApuesten() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("4...");
        Thread.sleep(1000);
        System.out.println("3....");
        Thread.sleep(1000);
        System.out.println("2....");
        Thread.sleep(1000);
        System.out.println("1....");
    }

    public void setBuffer(Casino buffer) {
        this.buffer = buffer;
    }

    public int getNumeroGenerado() {
        return numeroGenerado;
    }
}
