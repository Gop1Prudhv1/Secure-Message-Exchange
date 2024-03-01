import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ExchangeTestClass {
    public static void main(String[] args) {
        try {
            KeyPairGenerator alexKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
            alexKeyPairGenerator.initialize(512);
            KeyPair alexKeyPair = alexKeyPairGenerator.generateKeyPair();

            KeyPairGenerator bobKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
            bobKeyPairGenerator.initialize(512);
            KeyPair bobKeyPair = bobKeyPairGenerator.generateKeyPair();

            BlockingQueue<byte[]> alexQ = new LinkedBlockingQueue<>();
            BlockingQueue<byte[]> bobQ = new LinkedBlockingQueue<>();
            Queues queues = new Queues(bobQ, alexQ);

            AlexChat alexClass = new AlexChat(alexKeyPair, bobKeyPair.getPublic(), queues);
            BobChat bobClass = new BobChat(bobKeyPair, queues);

            Thread alexChat = new Thread(alexClass);
            Thread bobChat = new Thread(bobClass);

            alexChat.start();
            bobChat.start();

            alexChat.join();
            bobChat.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
