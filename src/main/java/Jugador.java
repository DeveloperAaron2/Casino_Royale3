public class Jugador extends Thread{
    private int dinero;
    private boolean resultadoUltimaApuesta;

    int numeroJugador;
    int apuestaPredeterminada;
    boolean aposte = false;


    private Casino buffer;
    public Jugador(int numeroJugador){
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
                int numeroApostado = (int) (Math.random() * 36);
                int apuestaAleatoria = (int) (Math.random() * 7);
                String apuesta = buffer.getTipoDeApuesta()[apuestaAleatoria];
                EsperarUnSegundo();
                Apostar(resultadoUltimaApuesta,apuesta,numeroApostado);
                aposte = true;
        }
        buffer.sumaUnoMenos(1);
    }
    public boolean isAposte() {
        return aposte;
    }

    private synchronized void Apostar(boolean resultadoUltimaApuesta, String apuesta, int numeroApostado) {
        if (resultadoUltimaApuesta) {
            buffer.apostar(300, apuesta, numeroApostado, numeroJugador);
        } else
            buffer.apostar(300, apuesta, numeroApostado, numeroJugador);
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
