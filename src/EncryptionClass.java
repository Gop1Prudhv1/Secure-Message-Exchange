import javax.crypto.*;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptionClass {
    static byte[] encrypt(byte s[], Cipher c, PublicKey pubKey) throws Exception
    {
        c.init(Cipher.ENCRYPT_MODE, pubKey);
        return c.doFinal(s);
    }

    static byte[] decrypt(byte s[], Cipher c, PrivateKey prvKey) throws Exception
    {
        c.init(Cipher.DECRYPT_MODE, prvKey);
        return c.doFinal(s);
    }
}
