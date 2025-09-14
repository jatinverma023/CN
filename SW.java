import java.util.Scanner;

class SlidingWindow {
    int windowSize;
    int totalPackets;
    int currentPacket;

    SlidingWindow(int windowSize, int totalPackets) {
        this.windowSize = windowSize;
        this.totalPackets = totalPackets;
        this.currentPacket = 0; // Start from the first packet
    }

    // Method to send packets
    public void sendPackets() {
        while (currentPacket < totalPackets) {
            // Send packets within the window limit
            for (int i = 0; i < windowSize && currentPacket < totalPackets; i++) {
                System.out.println("Sending packet " + currentPacket);
                currentPacket++;
            }
            // Simulate receiving an acknowledgment
            receiveAck();
        }
    }

    // Method to simulate receiving acknowledgment for the window
    private void receiveAck() {
        System.out.println("Acknowledgment received for window up to packet " +
                (currentPacket - 1));
        System.out.println("---------------------------------");
    }
}

public class SW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the window size: ");
        int windowSize = sc.nextInt();

        System.out.print("Enter the total number of packets: ");
        int totalPackets = sc.nextInt();

        SlidingWindow slidingWindow = new SlidingWindow(windowSize, totalPackets);
        slidingWindow.sendPackets(); // Start the packet transmission

        sc.close();
    }
}
