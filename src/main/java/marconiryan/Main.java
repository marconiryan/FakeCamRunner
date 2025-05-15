package marconiryan;

public class Main {
    public static void main(String[] args) {
        String roomUrl = null;
        int botCount = 3;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-u":
                    if (i + 1 < args.length) {
                        roomUrl = args[++i];
                    }
                    break;
                case "-b":
                    if (i + 1 < args.length) {
                        try {
                            botCount = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.err.println("Erro: Número de bots inválido");
                            System.exit(1);
                        }
                    }
                    break;
            }
        }

        if (roomUrl == null) {
            System.err.println("Erro: URL da sala é obrigatória. Use -u <url>");
            System.exit(1);
        }

        for (int i = 0; i < botCount; i++) {
            Thread t = new Thread(new BotRunner(i, roomUrl));
            t.start();
        }
    }

}