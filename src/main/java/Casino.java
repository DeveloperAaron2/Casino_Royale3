import java.util.ArrayList;

public class Casino {
    private int Dinero_Banca;
    private ArrayList<Jugador> jugadores;
    private int[] numerosRojos = new int[]{1,3,5,7,9,12,14,16,18,21,23,25,27,30,32,34,36};

    private boolean juegoFinalizado ;
    private String [] tipoDeApuesta = new String[]{"PAR","IMPAR","ROJO","NEGRO","1 A 12","13 A 24","25 A 36"};
    public boolean empezar = false;
    private Croupier croupier;
    private ArrayList<Integer> numerosRojosArray = new ArrayList<>();
    private int JugadoresPerdidos;

    public void Empezar(){
        croupier = new Croupier();
        croupier.setBuffer(this);
        EmpezarJuego();
        croupier.start();
        JugadoresPerdidos=0;
    }

    public Casino(int NumeroJugadores){
        empezar=false;
        Dinero_Banca = 50000;
        RellenaJugadores(NumeroJugadores);
        juegoFinalizado = false;
        ArrayList<Integer> numerosRojosArray = new ArrayList<>();
        rellenaArray(numerosRojosArray);
    }
    private void RellenaJugadores(int NumeroJugadores) {
        jugadores = new ArrayList<>();
        for (int i = 0; i < NumeroJugadores; i++) {
            jugadores.add(new Jugador(i));
            jugadores.get(i).setBuffer(this);
        }
    }

    private void rellenaArray(ArrayList<Integer> numerosRojosArray) {
        for (int numerosRojo : numerosRojos) {
            numerosRojosArray.add(numerosRojo);
        }
    }

    public void EmpezarJuego(){
        for (Jugador jugadore : jugadores) {
            jugadore.start();
        }
    }
    public boolean isJuegoFinalizado() {
        return juegoFinalizado;
    }

    public String[] getTipoDeApuesta() {
        return tipoDeApuesta;
    }

    public synchronized void apostar(int dineroApostado,String apuesta,int numeroApostado, int numeroJugador) {
        while(jugadores.get(numeroJugador).isAposte()){
            EsperarTurno();
        }
        Esperar();
        int numeroGenerado = croupier.getNumeroGenerado();
        jugadores.get(numeroJugador).setDinero((jugadores.get(numeroJugador).getDinero() - dineroApostado));
        Dinero_Banca +=dineroApostado;
        if(numeroGenerado == 0){
            System.out.println("todos los jugadores pierden porque el numero es 0");
        }
        else{
        switch (apuesta){
                case "PAR":
                    if(numeroApostado/2 == 0 && numeroGenerado/2 == 0)
                    {
                        AumentarDinero(dineroApostado, numeroJugador,2);
                        Dinero_Banca -= dineroApostado*2;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "IMPAR":
                    if(numeroApostado/2 != 0 && numeroGenerado/2 != 0)
                    {
                        AumentarDinero(dineroApostado, numeroJugador,2);
                        Dinero_Banca -= dineroApostado*2;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "ROJO":
                    if(numerosRojosArray.contains(numeroApostado) && numerosRojosArray.contains(numeroGenerado)){
                        AumentarDinero(dineroApostado, numeroJugador,2);
                        Dinero_Banca -= dineroApostado*2;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "NEGRO":
                    if(!numerosRojosArray.contains(numeroApostado) && !numerosRojosArray.contains(numeroGenerado)){
                        AumentarDinero(dineroApostado, numeroJugador,2);
                        Dinero_Banca -= dineroApostado*2;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "1 A 12":
                    ArrayList<Integer> UnoaDoce = new ArrayList<>();
                    for (int i = 1; i <= 12 ; i++) {
                        UnoaDoce.add(i);
                    }
                    if(UnoaDoce.contains(numeroApostado) && UnoaDoce.contains(numeroGenerado)){
                        AumentarDinero(dineroApostado, numeroJugador,3);
                        Dinero_Banca -= dineroApostado*3;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "13 A 24":
                    ArrayList<Integer> TreceaVeinticuatro = new ArrayList<>();
                    for (int i = 13; i <= 24 ; i++) {
                        TreceaVeinticuatro.add(i);
                    }
                    if(TreceaVeinticuatro.contains(numeroApostado) && TreceaVeinticuatro.contains(numeroGenerado)){
                        AumentarDinero(dineroApostado, numeroJugador,3);
                        Dinero_Banca -= dineroApostado*3;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
                case "25 A 36":
                    ArrayList<Integer> VeinticincoaTreintaySeis = new ArrayList<>();
                    for (int i = 25; i <= 36 ; i++) {
                        VeinticincoaTreintaySeis.add(i);
                    }
                    if(VeinticincoaTreintaySeis.contains(numeroApostado) && VeinticincoaTreintaySeis.contains(numeroGenerado)){
                        AumentarDinero(dineroApostado, numeroJugador,3);
                        Dinero_Banca -= dineroApostado*3;
                        jugadores.get(numeroJugador).setResultadoUltimaApuesta(true);
                        System.out.println("el jugador" + numeroJugador +" apuesta por " +  apuesta + " y gana");
                    }
                    else
                        System.out.println("el jugador" + numeroJugador + " apuesta por " +  apuesta + " y pierde");
                    break;
            }
        }
        }

    private synchronized void EsperarTurno() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void AumentarDinero(int dineroApostado, int numeroJugador, int multiplicador) {
        jugadores.get(numeroJugador).setDinero(jugadores.get(numeroJugador).getDinero() + (dineroApostado * multiplicador));
    }
    public void Esperar() {
        while(!empezar) {
            try {
               wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void setEmpezar(boolean empezar) {
        this.empezar = empezar;
        notifyAll();

    }

    public void setJuegoFinalizado(boolean juegoFinalizado) {
        this.juegoFinalizado = juegoFinalizado;
    }

    public void interrumpirHilos() {
        for (Jugador jugadore : jugadores) {
            jugadore.interrupt();
        }

    }
    public synchronized void sumaUnoMenos(int i) {
        JugadoresPerdidos+=i;
        if(JugadoresPerdidos==jugadores.size()){
            setJuegoFinalizado(true);
            notifyAll();
        }
    }

    public synchronized void AsignarTurnos() {
        for (Jugador jugadore : jugadores) {
            jugadore.setAposte(false);

        }
        notifyAll();
    }
}
