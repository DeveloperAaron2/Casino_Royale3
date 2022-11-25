public class Croupier extends Thread{
    public Casino buffer;
    private int numeroGenerado;

    @Override
    public void run() {
        while(!buffer.isJuegoFinalizado()){
            numeroGenerado =(int) (Math.random() * 37);
            buffer.empezar = false;
            try {
                Thread.sleep(1000);
                System.out.println("3....");
                Thread.sleep(1000);
                System.out.println("2....");
                Thread.sleep(1000);
                System.out.println("1....");
                Thread.sleep(1000);
                System.out.println("APUESTEN");
                Thread.sleep(4000);
                buffer.setEmpezar(true);
                buffer.AsignarTurnos();
                Thread.sleep(2000);
                buffer.setEmpezar(false);
                System.out.println("Cerramos apuestas");
                Thread.sleep(4000);
                System.out.println("Siguiente ronda");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
         }
    }
    public void setBuffer(Casino buffer) {
        this.buffer = buffer;
    }

    public int getNumeroGenerado() {
        return numeroGenerado;
    }
}
