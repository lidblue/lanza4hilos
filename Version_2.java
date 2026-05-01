package Version2;

public class Version_2 {

    static int contador = 0;

    // Método sincronizado
    public static synchronized void incrementar() {
        contador++;
    }

    // Clase que implementa la interfaz Runnable
    static class ClaseRunnable implements Runnable {

        private int repeticiones;

        public ClaseRunnable(int repeticiones) {
            this.repeticiones = repeticiones;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeticiones; i++) {
                incrementar();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int N = 100000; // Defino el número de incrementos por hilo

        Runnable tarea = new ClaseRunnable(N);

        Thread t1 = new Thread(tarea);
        Thread t2 = new Thread(tarea);
        Thread t3 = new Thread(tarea);
        Thread t4 = new Thread(tarea);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Resultado final (sincronizado): " + contador);
        System.out.println("Resultado esperado: " + (4 * N));
    }
}
