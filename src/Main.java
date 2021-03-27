public class Main {
    public static void main(String[] args) {
        readFile();
        unionFiles ();
        readPages ();

    }
    public static void readFile ()
    {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("demo.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
        }
        byte[] arr = out.toByteArray();
        System.out.println(Arrays.toString(arr));
        in.close();
        out.close();
    }
    public static void unionFiles ()
    {
        ArrayList<InputStream> al = new ArrayList<>();
        al.add (new FileInputStream ("1.txt"));
        al.add (new FileInputStream ("2.txt"));
        al.add (new FileInputStream ("3.txt"));
        al.add (new FileInputStream ("4.txt"));
        al.add (new FileInputStream ("5.txt"));
        SequenceInputStream in = new SequenceInputStream(Collections.enumeraton (al));
        ByteArrayOutputStream out = new ByteArrayOutputStream("nubers.txt");
        int x;
        while ((x = in.read()) != -1) {
            System.out.println((char) x);
        }
        in.close();
        out.close();
    }
    public static void readPages ()
    {
        int pageSize =  1800;
        RandomAccessFile file = new RandomAccessFile("book.txt", "r");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер страницу ");
        int pageNumber = sc.nextInt - 1;
        file.seek(pageNumber * pageSize);
        byte[] arr = new byte[1800];
        file.read(arr);
        System.out.println(Arrays.toString(arr));
        file.close();

    }
}

