package block;

public class BlockchainCode {

    public static void main(String[] args) {
        // Testing random transactions for the blockchain
        String Block1TXN = "Transaction 0";
        String Block2TXN = "Transaction 1";
        String Block3TXN = "Transaction 2";
        String Block4TXN = "Transaction 3";
        String Block5TXN = "Transaction 4";

        //A empty block for storing blocks in arraylist
        Block blockchain = new Block();

      /* There will be 5 blocks in this block chain.
      Block 1 Hash Value is 0 because the previous hash value of the block
      does not exist. */
        Block BLK1 = new Block(Block1TXN,"0");
        Block BLK2 = new Block(Block2TXN, BLK1.makeTHEHASH());
        Block BLK3 = new Block(Block3TXN, BLK2.makeTHEHASH());
        Block BLK4 = new Block(Block4TXN, BLK3.makeTHEHASH());
        Block BLK5 = new Block(Block5TXN, BLK4.makeTHEHASH());

        //Using the get hash method to get the hash value of previous blocks in each block
        System.out.println("Block 1 Hash Value: " + BLK1.makeTHEHASH());
        System.out.println("Block 2 Hash Value: " + BLK2.makeTHEHASH());
        System.out.println("Block 3 Hash Value: " + BLK3.makeTHEHASH());
        System.out.println("Block 4 Hash Value: " + BLK4.makeTHEHASH());
        System.out.println("Block 5 Hash Value: " + BLK5.makeTHEHASH());

        //Adding the 4 blocks to blockchain arraylist
        blockchain.addBlocktoChain(BLK1);
        blockchain.addBlocktoChain(BLK2);
        blockchain.addBlocktoChain(BLK3);
        blockchain.addBlocktoChain(BLK4);
        blockchain.addBlocktoChain(BLK5);

      /*Checks if all the hash values and previous hash values match or not
      In other words, it is checking if the blockchain itself is valid or not */
        blockchain.Authenticate();

    }
}