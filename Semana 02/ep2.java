import java.util.*;

class MoM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        Map<String, List<String>> voos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] cidades = scanner.nextLine().split(" ");
            String origem = cidades[0];
            String destino = cidades[1];

            voos.computeIfAbsent(origem, k -> new ArrayList<>()).add(destino);
        }

        List<String> cidades = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String cidade = scanner.nextLine();
            if (cidade.isEmpty()) {
                break;
            }
            cidades.add(cidade);
        }

        for (String cidade : cidades) {
            boolean eSegura = cidadeESegura(cidade, voos, new HashSet<>(), new HashSet<>());
            System.out.println(cidade + (eSegura ? " safe" : " trapped"));
        }
        scanner.close();
    }

    private static boolean cidadeESegura(String cidade, Map<String, List<String>> voos, Set<String> visitado, Set<String> ciclo) {
        if (visitado.contains(cidade)) {
            return ciclo.contains(cidade);
        }

        visitado.add(cidade);

        if (!voos.containsKey(cidade)) {
            visitado.remove(cidade);
            return false;
        }

        ciclo.add(cidade);

        for (String destino : voos.get(cidade)) {
            if (cidadeESegura(destino, voos, visitado, ciclo)) {
                return true;
            }
        }

        ciclo.remove(cidade);
        visitado.remove(cidade);
        return false;
    }
}