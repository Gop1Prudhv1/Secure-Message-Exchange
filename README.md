# RSA Key Exchange Implementation

This program demonstrates a secure communication system between two parties, Alex and Bob, using the RSA algorithm for key exchange. 
The exchange is implemented through three classes: Alex, Bob, and ExchangeTest, utilizing multi-threaded programming.

Alex Class:
1.Generates a secret key for the AES algorithm with the generateKey() operation.
2.Signs and encrypts the secret key using RSA before sending it to Bob.
3.Encrypts and sends a confidential message to Bob.
4.Decrypts and displays Bob's reply.


Bob Class:
1.Receives the encrypted and signed secret key from Alex.
2.Decrypts and verifies the secret key using RSA.
3.Reads and decrypts Alex's message, displaying it on the screen.
4.Sends a reply encrypted with the secret key to Alex.

ExchangeTest Class:
1.Creates public and private RSA key pairs for both Alex and Bob.
2.Sets the keys for each corresponding class.
3.Instantiates the Alex and Bob classes.

Implementation Notes:
Both Alex and Bob classes use queues (Alex Q and Bob Q) for communication, providing send() and read() operations.
The program is designed for multi-threaded programming to ensure concurrent and secure communication.
