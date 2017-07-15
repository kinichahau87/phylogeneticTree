/**
 * @author Kevin Morfin
 */
import java.util.ArrayList;


public class consensus_Tree {
	
	public ArrayList<PyTree> arrayTrees = new ArrayList<PyTree>();
	public ArrayList<ArrayList<TreeNode>> treenode = new ArrayList<ArrayList<TreeNode>>();
	public PyTree consesTree;
	private double  comp [][];
	int sNum;
	int bNum;
	int compRow;
	int compCol;
	
	public consensus_Tree(int specNumber, int branchNumber){
		consesTree = new PyTree(specNumber,branchNumber);
		sNum = specNumber;
		bNum = branchNumber;
				
	}
	
	
	public void addTree(PyTree tree){
		arrayTrees.add(tree);
	}
	
	public void addPyTrees(){
		
		for (int i = 0; i < arrayTrees.size(); i++){
			treeTraverse(arrayTrees.get(i));
		}
		
		
	}
	
	/**Used to find the consensus branches**/
	public void find_consen(){
		double max;
		double bcount = (double)bNum;
		int p;
		//initialize comparison table
		int compRow = sNum+ bNum ;
		int compCol = treenode.size();
		comp = new double[compRow][compCol];
		
		//fill in the comparison table
		for (int i = 0; i < compRow; i++){
			
			for (int j = 0; j < compCol; j++){
				comp[i][j] = 100.00;
			}//end of for
			
		}//end of for
		
		for (int i = 0; i < compRow; i++){
			
			for (int j = 0; j < compCol; j++){
				if (treenode.get(j).get(i).taxa != treenode.get(j).get(i).taxa)
					comp[j][i] = comp[j][i] - ((1/bcount)*comp[j][i]); 	//calculate majority percentage
			}//end of for
			
		}//end of for
		
		
		for (int i = 0; i < compRow; i++){
			max = comp[i][0];
			p = 0;
			for (int j = 0; j < compCol; j++){
				if (comp[i][j] > max){
					max = comp[i][j];
					p = j;
				}
			}//end of for
			
			consesTree.insertNode(treenode.get(p).get(i).data, treenode.get(p).get(i).taxa, treenode.get(p).get(i).is_comm_AnsP);
			
		}//end of for
		
		
		System.out.printf("\n\n-A post-order traversal of the Majority consensus Tree looks like this : \n");
		
		treeTrav();
	
	}//end of findconsen
	
	/**Used to traverse the tree**/
	public void treeTrav(){
		treeHelp(consesTree.root);
	}
	
	public void treeHelp(TreeNode node){
		
		if (node == null)
			return;
		
		treeHelp(node.leftNode);
		treeHelp(node.rightNode);
		if (!node.is_comm_AnsP){
			System.out.printf("%s, ", node.taxa);
		}
	}
	
	/**Used to build the consensus tree**/
	
	public void treeTraverse(PyTree node1){
		
		ArrayList<TreeNode> array1 = new ArrayList<TreeNode>();
		treeTraverseHelper(node1.root,array1);
		treenode.add(array1);
		
	}//end of treeTraverse
	
	private void treeTraverseHelper(TreeNode node1, ArrayList<TreeNode> tree){
		
		if (node1 == null)
			return;
		
		if (!tree.contains(node1))
			tree.add(node1);
						
		treeTraverseHelper(node1.leftNode, tree);
		treeTraverseHelper(node1.rightNode, tree);
			
		
		
	}//end of treeTraverseHelper

}//end of consensus_Tree
