import java.util.*;

class Site {
    int id;
    int time;

    public Site(int id, int time) {
        this.id = id;
        this.time = time;
    }
}

class BankRobbery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int N = scanner.nextInt(); // número de sites
            int M = scanner.nextInt(); // número de estradas
            int B = scanner.nextInt(); // número de bancos
            int P = scanner.nextInt(); // número de estações de polícia

            List<List<Site>> graph = new ArrayList<>();
            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; ++i) {
                int U = scanner.nextInt();
                int V = scanner.nextInt();
                int T = scanner.nextInt();
                graph.get(U).add(new Site(V, T));
                graph.get(V).add(new Site(U, T));
            }

            Set<Integer> banks = new HashSet<>();
            for (int i = 0; i < B; ++i) {
                banks.add(scanner.nextInt());
            }

            Set<Integer> policeStations = new HashSet<>();
            for (int i = 0; i < P; ++i) {
                policeStations.add(scanner.nextInt());
            }

            int[] minTimeToPolice = new int[N];
            Arrays.fill(minTimeToPolice, Integer.MAX_VALUE);

            for (int station : policeStations) {
                dijkstra(graph, minTimeToPolice, station);
            }

            int maxDistance = 0;
            int count = 0;
            for (int bank : banks) {
                if (minTimeToPolice[bank] == Integer.MAX_VALUE) {
                    continue; // o banco não é alcançável por nenhuma estação de polícia
                }

                if (minTimeToPolice[bank] > maxDistance) {
                    maxDistance = minTimeToPolice[bank];
                    count = 1;
                } else if (minTimeToPolice[bank] == maxDistance) {
                    count++;
                }
            }

            System.out.println(count + " " + (maxDistance == Integer.MAX_VALUE ? "*" : maxDistance));

            List<Integer> maxDistanceBanks = new ArrayList<>();
            for (int bank : banks) {
                if (minTimeToPolice[bank] == maxDistance) {
                    maxDistanceBanks.add(bank);
                }
            }

            Collections.sort(maxDistanceBanks);
            for (int i = 0; i < maxDistanceBanks.size(); ++i) {
                System.out.print(maxDistanceBanks.get(i));
                if (i < maxDistanceBanks.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void dijkstra(List<List<Site>> graph, int[] minTime, int source) {
        PriorityQueue<Site> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(s -> s.time));
        priorityQueue.add(new Site(source, 0));
        minTime[source] = 0;

        while (!priorityQueue.isEmpty()) {
            Site current = priorityQueue.poll();

            for (Site neighbor : graph.get(current.id)) {
                int newTime = current.time + neighbor.time;
                if (newTime < minTime[neighbor.id]) {
                    minTime[neighbor.id] = newTime;
                    priorityQueue.add(new Site(neighbor.id, newTime));
                }
            }
        }
    }
}
