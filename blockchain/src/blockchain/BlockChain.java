package blockchain;

import java.util.ArrayList;

public class BlockChain {
	public static ArrayList<Block> blockchain = new ArrayList<>();
	public static int difficulty = 5;

	public static void main(String[] args) {	
		
		blockchain.add(new Block("Genesis Block", "0"));
		System.out.println("Trying to Mine block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Third Block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		blockchain.add(new Block("Fourth Block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 4...");
		blockchain.get(3).mineBlock(difficulty);
		
		blockchain.add(new Block("Fifth Block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 5...");
		blockchain.get(4).mineBlock(difficulty);
		
		System.out.println("Blockchain is Valid: " + isChainValid());
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("현재 해시가 일치하지 않음");			
				return false;
			}
			
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("이전 해시와 일치하지 않음");
				return false;
			}
			
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("채굴된 적 없는 블럭임");
				return false;
			}
		}
		
		return true;
	}
	
}