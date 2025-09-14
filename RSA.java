import java.util.Scanner;

class LeakyBucket {
    private int[] q;
    private int front = 0, rear = 0, capacity;

    public LeakyBucket(int size) {
        capacity = size;
        q = new int[size];
    }

    // Insert packet into bucket
    public void insert(int ele) {
        if (rear == capacity) {
            System.out.println("Bucket full! Packet lost: " + ele);
        } else {
            q[rear++] = ele;
            System.out.println("Packet inserted: " + ele);
        }
    }

    // Leak packets one by one at fixed rate
    public void leak() {
        if (rear == front) {
            System.out.println("Bucket empty. No packets to leak.");
        } else {
            System.out.println("\nLeaking packets...");
            while (front < rear) {
                try {
                    Thread.sleep(1000); // 1 second delay per leak
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Leaked packet: " + q[front]);
                front++;
            }
        }
    }
}

public class Leaky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User defines bucket size
        System.out.print("Enter the bucket (queue) capacity: ");
        int size = sc.nextInt();
        LeakyBucket bucket = new LeakyBucket(size);

        // User inputs packets
        System.out.print("Enter number of packets to send: ");
        int n = sc.nextInt();

        System.out.println("Enter the packets:");
        for (int i = 0; i < n; i++) {
            int ele = sc.nextInt();
            bucket.insert(ele);
        }

        // Leak packets
        bucket.leak();

        sc.close();
    }
}
