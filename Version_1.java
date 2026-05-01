package Version1;

public class Version_1 {

    static int contador = 0; // variable compartida NO sincronizada

    // Clase que implementa Runnable
    static class ClaseRunnable implements Runnable {

        private int repeticiones;

        public ClaseRunnable(int repeticiones) {
            this.repeticiones = repeticiones;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeticiones; i++) {
                contador++; // operación NO atómica → condición de carrera
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int N = 100000; // Número de incrementos por hilo

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

        System.out.println("Resultado final (sin sincronizar): " + contador);
        System.out.println("Resultado esperado: " + (4 * N));
    }
}

