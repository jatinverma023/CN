import java.util.Scanner;

public class BellmanFordAlgorithm {
    private int[] D;
    private int num_ver;
    public static final int INF = Integer.MAX_VALUE / 2; // Avoid overflow

    // Constructor
    public BellmanFordAlgorithm(int n) {
        this.num_ver = n;
        D = new int[n + 1]; // Using 1-based indexing
    }

    public void shortest(int s, int[][] A) {
        // Initialize distances
        for (int i = 1; i <= num_ver; i++) {
            D[i] = INF;
        }
        D[s] = 0;

        // Relax edges (num_ver - 1) times
        for (int k = 1; k <= num_ver - 1; k++) {
            for (int i = 1; i <= num_ver; i++) {
                for (int j = 1; j <= num_ver; j++) {
                    if (A[i][j] != INF) {
                        if (D[i] != INF && D[j] > D[i] + A[i][j]) {
                            D[j] = D[i] + A[i][j];
                        }
                    }
                }
            }
        }

        // Check for negative cycles
        for (int i = 1; i <= num_ver; i++) {
            for (int j = 1; j <= num_ver; j++) {
                if (A[i][j] != INF) {
                    if (D[i] != INF && D[j] > D[i] + A[i][j]) {
                        System.out.println("The Graph contains a negative edge cycle");
                        return;
                    }
                }
            }
        }

        // Print results
        for (int i = 1; i <= num_ver; i++) {
            if (D[i] == INF)
                System.out.println("Distance of source " + s + " to " + i + " is INF");
            else
                System.out.println("Distance of source " + s + " to " + i + " is " + D[i]);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of vertices
        System.out.println("Enter the number of vertices: ");
        int n = sc.nextInt();

        int[][] A = new int[n + 1][n + 1];

        // Input weighted adjacency matrix
        System.out.println("Enter the weighted adjacency matrix (0 for no edge, except diagonal): ");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                A[i][j] = sc.nextInt();

                if (i == j) {
                    A[i][j] = 0;
                } else if (A[i][j] == 0) {
                    A[i][j] = INF;
                }
            }
        }

        // Source vertex
        System.out.println("Enter the source vertex: ");
        int s = sc.nextInt();

        // Run Bellman-Ford
        BellmanFordAlgorithm b = new BellmanFordAlgorithm(n);
        b.shortest(s, A);

        sc.close();
    }
}
