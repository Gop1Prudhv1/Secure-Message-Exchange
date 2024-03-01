import javax.crypto.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class AlexChat implements Runnable {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SecretKey secretKey;
    private PublicKey bobPublicKey;
    private Queues queues;


    public AlexChat(KeyPair keyPair, PublicKey bobPubKey, Queues queues) {
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.bobPublicKey = bobPubKey;
        this.queues = queues;
    }


    public void generateKey() throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        secretKey = keygen.generateKey();
    }

    public void sendKey() throws Exception {
        Cipher ciph = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] encryptedKey = EncryptionClass.encrypt(secretKey.getEncoded(), ciph, bobPublicKey);
        queues.getBobQueue().put(encryptedKey);
    }

    public void sendMessage() throws Exception {
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = ciph.doFinal("Let us have a meeting tomorrow at 4".getBytes());
        queues.getBobQueue().put(encryptedMessage);
    }

    public void receiveReply() throws Exception {
        byte[] reply = queues.getAlexQueue().take();
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.DECRYPT_MODE, secretKey);
        System.out.println("Reply from Bob: " + new String(ciph.doFinal(reply)));
    }

    @Override
    public void run() {
        try {
            generateKey();
            sendKey();
            sendMessage();
            receiveReply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
