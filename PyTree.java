/**
 * @author Kevin Morfin
 */
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PyTree {
	
	public TreeNode root;		//root of the phylogenetic tree
	public Map<Integer,String> specMap;		//Map of all specimens in the tree
	public ArrayList<Integer> specPoints = new ArrayList<Integer>();
	public byte splitTable[][];		//this table is used to count the branchcount of each specimen
	private int branchCount = 0;		//branch counter
	private int sNum;		//number of specimens in the tree
	private int bNum;		//number of branches in the tree
	
	
	public PyTree(int specNum, int branchNum){
		root = null;
		specMap = new HashMap<Integer,String>();
		splitTable = new byte[specNum][branchNum];
		sNum = specNum;
		bNum = branchNum;
		
	}//end of constructor
	
	/**Used to insert a node in the phylogenetic tree
	 * 
	 **/
	public void insertNode(int data, String name,boolean point){
		
		if (root == null){
			
			if (point){
				root = new TreeNode(data,name,point,branchCount);
				branchCount++;
			}
			else{
				root = new TreeNode(data,name,point,-1);
				
			}
		}
		else{
			if (point){
				root.insert(data, name,point,branchCount);
				branchCount++;
			}
			else{
				root.insert(data, name,point,-1);
				
			}
			
		}
		
		if (!point){
			specPoints.add(data);
			
			for (int i = 0; i < specPoints.size(); i++){
				 
				 // build map for specimen
				 if (!specMap.containsKey(i))
					 specMap.put(i, name);
			}
			
			int temp;
			String stemp = "";
					
			 for (int i = 0; i < specPoints.size(); i++){
				 
				
				 
				 for (int j = 0; j < specPoints.size(); j++){
					 if (specPoints.get(i) < specPoints.get(j)){
						 temp = specPoints.get(i);
						 specPoints.set(i,specPoints.get(j));
						 specPoints.set(j,temp);
						 stemp = specMap.get(i);
						 specMap.put(i, specMap.get(j));
						 specMap.put(j,stemp);
					 }//end of if
					 
				 }//end of inner for loop
				 
			 }//end of for loop
			 
		}//end of if
		
	}//end of insetNode
	
	
	/**Used to traverse the tree**/
	public void treeTraverse(){
		
		treeTraverseHelper(root);
	}//end of treeTraverse
	
	private void treeTraverseHelper(TreeNode node){
		
		if (node == null)
			return;
		
			
						
		treeTraverseHelper(node.leftNode);
		treeTraverseHelper(node.rightNode);
		
		/**The main algorithm for partitioning the tree is breath_first search
		*if the node is a common point then traverse through the rest of the tree
		*to find all node under this node
		*/
		if (node.is_comm_AnsP)
			calcSplit(node,node.branchCount);
			
		
		
	}//end of treeTraverseHelper
	
	public void calcSplit(TreeNode branch,int branchN){
		if (branch == null)
			return;
		
		
						
		
		calcSplit(branch.leftNode,branchN);
		calcSplit(branch.rightNode,branchN);
		
		//fill in the table with number of branches for specimen
				if (specMap.containsValue(branch.taxa)){
					splitTable[specPoints.indexOf(branch.data) ][branchN] = 1;
					branch.branchCount++;
						
				}
				
	}//end of calcSplit
	
	//display all groups of the phylogenetic tree including the monophyletic group
	public void build_Newrick(){
				
	
		int array [] = new int[sNum];
		boolean hit [] = new boolean[sNum];
		int count = 0;
		int specCount[] = new int[sNum];
		int max;
		int p;
		int groupNumber = 0;;
		
		ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
		
		
		//calculate
		for (int i = 0; i < sNum; i++){
			
			for (int j = bNum -1; j >= 0; j--){
				array[i] = array[i] + (splitTable[i][j] *(int) Math.pow(2,count));
				count++;
			}
			count = 0;
			
		
		}
		
	  
		for (int i = sNum - 1; i > 0; i--){
			ArrayList<String> names = new ArrayList<String>();
			
			
			
			for (int j = 0; j < sNum; j++){
				if (array[i] == array[j] && hit[j] != true){
					names.add(specMap.get(j));
					hit[j] = true;
				}//end of if
			}//end of for
			
			   if (names.size() != 0)
				groups.add(names);
		
		}//end of for
		
		System.out.printf("After partitioning the phylogenetic tree there are %d different groups in the tree :\n", groups.size());
		
		for (int i = 0; i < groups.size(); i++){
			System.out.printf("Group %d = ",i);
			for (int j = 0; j < groups.get(i).size(); j++){
				System.out.printf(" " +groups.get(i).get(j) + " ");
				
			}
			
			System.out.printf(",(");
			for (int n = i + 1; n < groups.size(); n++){
				
				for (int m = 0; m < groups.get(n).size(); m++){
					
					System.out.printf(" "+groups.get(n).get(m)+ " ");
				}
				
			}
			System.out.printf(")");
			
			System.out.println();
		}
		
		//find the monophyletic group
		for (int i = 0; i < sNum; i++){
			
			for (int j = 0; j < bNum; j++){
				if (splitTable[i][j] == 1)
					specCount[i] = specCount[i] + 1;
				
			}
		}
		
		max = specCount[0];
		p = 0;
		for (int i = 0; i < specCount.length; i++){
			if (specCount[i] > max){
				max = specCount[i];
				p = i;
			}
		}
		
		for (int i = 0; i < groups.size(); i++){
			if (groups.get(i).contains(specMap.get(p))){
				groupNumber = i;
				break;
			}
		}
		
		System.out.printf("\nGroup %d is Monophyletic group", groupNumber);
		
	}//end of build_Newrick

}//end of class
