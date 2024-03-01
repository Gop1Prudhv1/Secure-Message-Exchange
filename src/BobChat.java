import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class BobChat implements Runnable {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SecretKey secretKey;
    private Queues queues;

    public BobChat(KeyPair keyPair, Queues queues) {
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.queues = queues;
    }


    public void receiveKey() throws Exception {

        Cipher ciph = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] encryptedKey = queues.getBobQueue().take();

        byte[] decryptedKey = EncryptionClass.decrypt(encryptedKey, ciph, privateKey);
        secretKey = new SecretKeySpec(decryptedKey, "AES");
    }

    public void receiveMessage() throws Exception {
        byte[] msg = queues.getBobQueue().take();
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.DECRYPT_MODE, secretKey);
        System.out.println("Alex's message: " + new String(ciph.doFinal(msg)));
    }

    public void sendReply() throws Exception {
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedReply = ciph.doFinal("Yes, I can meet you at Student Union".getBytes());
        queues.getAlexQueue().put(encryptedReply);
    }

    @Override
    public void run() {
        try {
            receiveKey();
            receiveMessage();
            sendReply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
