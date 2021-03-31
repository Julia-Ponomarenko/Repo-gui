public class Main {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';


    public static void main(String[] args) {
        Main w = new Main();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MFU {
        Object scan = new Object();
        Object print = new Object();

        public static void main(String[] args) {
            MFU mfu = new MFU();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.print("Документ 1", 3);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.scan("Документ 2", 7, 1);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.scan("Документ 3", 8, 2);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.scan("Документ 4", 4, 3);
                }
            }).start();
        }


        public void print(String doc, int n) {
            synchronized (print) {
                System.out.println("Начало печати");
                for (int i = 0; i < n; i++) {
                    System.out.println(i);
                }
                System.out.println("Конец печати");
            }

        }

        public void scan(String doc, int x, int caseNumber) {
            synchronized (scan) {
                System.out.println("Начало сканирования");
                switch (caseNumber) {
                    case 1:
                        System.out.println("Сканирование в файл");
                        for (int i = 0; i < x; i++) {
                            System.out.println(i);
                        }
                        break;
                    case 2:
                        System.out.println("Сканирование на почту");
                        for (int i = 0; i < x; i++) {
                            System.out.println(i);
                        }
                        break;
                    case 3:
                        synchronized (print) {
                            System.out.println("Ксерокопия начало");
                            for (int i = 0; i < x; i++) {
                                System.out.println(i);
                            }
                            System.out.println("Ксерокопия конец");
                            break;
                        }
                }
                System.out.println("Конец сканирования");
            }
        }
    }
}
