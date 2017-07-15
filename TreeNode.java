/**class TreeNode is node of phylogenetic tree
 * 
 * @author Kevin Morfin
 *
 */
public class TreeNode {
	
	TreeNode rightNode;		//right child of node
	TreeNode leftNode;		//left child of node
	boolean is_comm_AnsP;		//if the node is common point on the tree this is true
	int data;					//position of node in the tree
	String taxa;				//name of the specimen
	int branchCount;			//how many branches this node is under
	
	
	
	public TreeNode(int data, String name, boolean point, int branchNum){
		this.data = data;
		rightNode = null;
		leftNode = null;
		taxa = name;
		is_comm_AnsP = point;
		branchCount = branchNum;
	}//end of constructor
	
	/**Used to insert a node into a tree**/
	public void insert(int data, String name, boolean point, int branchNum){
		
		if (data < this.data){
			
			if (leftNode == null)
				leftNode = new TreeNode(data,name,point,branchNum);
			else
				leftNode.insert(data, name,point,branchNum);
		}
		else if (data > this.data){
			
			if (rightNode == null)
				rightNode = new TreeNode(data,name,point,branchNum);
			else
				rightNode.insert(data, name,point,branchNum);
			
		}//end of else-if
		
	}//end of insert

}//end of class
