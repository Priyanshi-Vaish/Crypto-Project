package block;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

/* In a blockchain, a block has a digital signature, a hash value for the current and previous block, the actual transaction, and a date a time of the transaction to know when the transaction occurred. All of these will be strings except for the Date/Time; The digital signature and hash value will be public but the date/time and the actual TXN will be private for the user's privacy */

public class Block {
    public ArrayList<Block> blockchain = new ArrayList<Block>();
    public String DigitalSignature;                // Digital signature
    public String HashValuePrev;                 // Hash value of the previous block
    private long DateTime;                         // Date + time of the transaction
    private String TXN;                            // TXN = Transaction
    private int nonce;                             // Arbitrary number to randomize hash values

    //Added an empty constructor to store all the information for the blockchain
    Block(){
    }

    /* We need a previous hash value for the current or first block
    For that, we need to create an empty non-real block */
    Block(String Transaction, String PrevHashValue){
        this.HashValuePrev = PrevHashValue;   //Creates the Previous hash value of the block
        this.TXN = Transaction;               //Creates the transaction
        this.DateTime = createDateTime();     //Creates Date time in milliseconds
        this.DigitalSignature = makeTHEHASH();
    }

    // Get the current hash value for the current block in question
    public String getTHEHASH(){
        return this.DigitalSignature;
    }

    // Get the previous hash value for the current / first block
    public String getPreviousHashValue(){
        return this.HashValuePrev;
    }

    /* The transaction needs to have a date and time so the user knows
    when the transaction occurred */
    public long createDateTime(){
        Date BLKDate = new Date();
        long time = BLKDate.getTime();   // This will return the time in milliseconds
        return time;
    }

   /* Now we have to make the HASH, we have to put together the
    previous hash value, the digital signature, date-time, etc.
    We have to convert the long time in milliseconds to a string
    To make the create the Hash, we have to make the current signature
    We do that adding the time value, the actual transaction and the
    previous hash value of the blocks */

    public String makeTHEHASH(){
        String DateTimetoString = Long.toString(DateTime);  // Convert time to string value
        String makeTHEHASH = HashValuePrev + DateTimetoString + TXN; // Compile to make digital signature for the current block
        String hash = encryptTXN(makeTHEHASH);       // Previous hash values to make the new hash
        return hash;             // Return hash value
    }

    // Hash algorithm
    public String encryptTXN(String TXN){
        try {
            MessageDigest PROG = MessageDigest.getInstance("SHA-256");   // SHA-256 ALGORITHM
            byte[] cipheredHASH = PROG.digest(TXN.getBytes(StandardCharsets.UTF_8));
            StringBuffer K = new StringBuffer();         // Put all ciphered hash into one string
            for (int i = 0; i < cipheredHASH.length; i++) {
                // Taking the hash just ciphered from the SHA-256 algorithm and converting to hex
                String hex = Integer.toHexString(0xff & cipheredHASH[i]);
                K.append(hex);              //Adding hex values to String buffer K
                if(hex.length() == 1){             //Add 0s when the hex length is only 1
                    K.append('0');
                }
            }
            String encrypted = K.toString(); // Change string buffer K to string datatype
            return encrypted;
        }
        // In case netbeans doesn't support SHA-256 algorithm
        catch (NoSuchAlgorithmException ex) {
            java.util.logging.Logger.getLogger(Block.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addBlocktoChain(Block block){
        blockchain.add(block);                  // Add all blocks to the actual blockchain
    }
    // Checks if the blockchain itself is valid or not
    public void Authenticate(){
        Block beforeBLK;        //Before and present hash values have to be equal
        Block presentBLK;
        int count = 0;           // Count the blocks
        for(int i = 1; i < blockchain.size(); i++){
            presentBLK = blockchain.get(i);
            beforeBLK = blockchain.get(i-1);
            if(presentBLK.getPreviousHashValue().equals(beforeBLK.makeTHEHASH())){
                System.out.println("Block " + i+ " Hash Value" + " = Block " + (i+1) + " Previous Hash Value");
                count++;
            }
            else{
                System.out.println("Block " + (i-1)+ " Hash Value" + " = Block " + i + " Previous Hash Value");
            }
        }

        // if all the blocks in the blockchain are valid, then the blockchain itself is valid */
        if(count == blockchain.size() - 1){
            System.out.println("\nBlockchain = VALID!");
        }
        else{
            System.out.println("Blockchain = NOT VALID!");
        }
    }
}