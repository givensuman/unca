public class App {
    public static void main(String[] args) throws Exception {
        StringQueue q = new StringQueue();
        q.enqueue("A");
        System.out.println(q); // "A"
        q.enqueue("BB");
        System.out.println(q); // "A:BB"
        System.out.println(q.count()); // "2"
        q.enqueue("CCC");
        System.out.println(q); // "A:BB:CCC"
        System.out.println(q.count()); // "3"
        String r = q.dequeue();
        System.out.println(r); // "A"
        System.out.println(q); // "BB:CCC"
        System.out.println(q.count()); // "2"
    }
}
