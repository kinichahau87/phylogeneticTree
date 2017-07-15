/**
 * @author Kevin Morfin
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class PyTreeUnitTest {
	
	/**
	 * The BRACA1 protein sequences of 5 species were used using the databases in swissprot and Blast.
	 * species are
	 * Bovin,Gorgo,Canfa,Macmu,Ponpy
	 * 
	 */
	
	/**test object for PyTree **/
	PyTree test1 = new PyTree(5,4);
	PyTree test2  = new PyTree(5,4);
	PyTree test3 = new PyTree(5,4);
	
	/**test object for consensus_Tree **/
	PyTree testCon1 = new PyTree(5,4);
	PyTree testCon2 = new PyTree(5,4);
	PyTree testCon3 = new PyTree(5,4);
	consensus_Tree conTest = new consensus_Tree(5,4);

	@Before
	public void setUp() throws Exception {
		
		/**Run PyTree class for all three threes**/
		/**Tree one**/
		test1.insertNode(100, "comm", true);
		test1.insertNode(90, "comm2",true);
		test1.insertNode(150, "comm3",true);
		test1.insertNode(70,"comm4",true);
		test1.insertNode(95, "Macmu",false);
		test1.insertNode(140, "Gorgo",false);
		test1.insertNode(160, "Ponpy",false);
		test1.insertNode(65, "Bovin",false);
		test1.insertNode(75, "Canfa",false);
		
				
		test1.treeTraverse();
		test1.build_Newrick();
		
		/**Tree two**/
		test2.insertNode(100, "comm", true);
		test2.insertNode(90, "comm2",true);
		test2.insertNode(150, "comm3",true);
		test2.insertNode(70,"comm4",true);
		test2.insertNode(95, "Macmu",false);
		test2.insertNode(140, "Gorgo",false);
		test2.insertNode(160, "Ponpy",false);
		test2.insertNode(65, "Canfa",false);
		test2.insertNode(75, "Bovin",false);
		
		
		
		test2.treeTraverse();
		test2.build_Newrick();
		
		/**Tree three**/
		test3.insertNode(100, "comm", true);
		test3.insertNode(90, "comm2",true);
		test3.insertNode(150, "comm3",true);
		test3.insertNode(70,"comm4",true);
		test3.insertNode(95, "Macmu",false);
		test3.insertNode(140, "Gorgo",false);
		test3.insertNode(160, "Ponpy",false);
		test3.insertNode(65, "Bovin",false);
		test3.insertNode(75, "Canfa",false);
		
		
		test3.treeTraverse();
		test3.build_Newrick();
		
		
		/**Run consensus_Tree class**/
		/**PyTree 1**/
		testCon1.insertNode(100, "comm", true);
		testCon1.insertNode(99, "hudra",false);
		testCon1.insertNode(150, "comm2",true);
		testCon1.insertNode(135,"comm3",true);
		testCon1.insertNode(130, "Artemia",false);
		testCon1.insertNode(140, "Drosophila",false);
		testCon1.insertNode(160, "comm4",true);
		testCon1.insertNode(158, "Catostomus",false);
		testCon1.insertNode(180, "comm5",true);
		testCon1.insertNode(170, "xenopus",false);
		testCon1.insertNode(190, "comm6",true);
		testCon1.insertNode(185, "chicken",false);
		testCon1.insertNode(195, "human",false);
		
		/**PyTree 2**/
		testCon2.insertNode(100, "comm", true);
		testCon2.insertNode(99, "hudra",false);
		testCon2.insertNode(150, "comm2",true);
		testCon2.insertNode(135,"comm3",true);
		testCon2.insertNode(130, "Artemia",false);
		testCon2.insertNode(140, "Drosophila",false);
		testCon2.insertNode(160, "comm4",true);
		testCon2.insertNode(158, "Catostomus",false);
		testCon2.insertNode(180, "comm5",true);
		testCon2.insertNode(170, "xenopus",false);
		testCon2.insertNode(190, "comm6",true);
		testCon2.insertNode(185, "human",false);
		testCon2.insertNode(195, "chicken",false);
		
		/**PyTree 3**/
		testCon3.insertNode(100, "comm", true);
		testCon3.insertNode(99, "hudra",false);
		testCon3.insertNode(150, "comm2",true);
		testCon3.insertNode(135,"comm3",true);
		testCon3.insertNode(130, "Artemia",false);
		testCon3.insertNode(140, "Drosophila",false);
		testCon3.insertNode(160, "comm4",true);
		testCon3.insertNode(158, "Catostomus",false);
		testCon3.insertNode(180, "comm5",true);
		testCon3.insertNode(170, "xenopus",false);
		testCon3.insertNode(190, "comm6",true);
		testCon3.insertNode(185, "human",false);
		testCon3.insertNode(195, "chicken",false);
		
		conTest.addTree(testCon1);
		conTest.addTree(testCon2);
		conTest.addTree(testCon3);
		
		conTest.addPyTrees();
		
		
		conTest.find_consen();
				
		
		
	}

	@Test
	public void test() {
		/**Test the intregity of the code of first tree**/
		assertTrue(test1.specMap.containsKey(0));
		assertEquals("Bovin",test1.specMap.get(0));
		assertTrue(test1.specMap.containsKey(1));
		assertEquals("Canfa",test1.specMap.get(1));
		assertTrue(test1.specMap.containsKey(2));
		assertEquals("Macmu",test1.specMap.get(2));
		assertTrue(test1.specMap.containsKey(3));
		assertEquals("Gorgo",test1.specMap.get(3));
		assertTrue(test1.specMap.containsKey(4));
		assertEquals("Ponpy",test1.specMap.get(4));
		
		/**Test the intregity of the code of second tree**/
		assertTrue(test2.specMap.containsKey(0));
		assertEquals("Canfa",test2.specMap.get(0));
		assertTrue(test2.specMap.containsKey(1));
		assertEquals("Bovin",test2.specMap.get(1));
		assertTrue(test2.specMap.containsKey(2));
		assertEquals("Macmu",test2.specMap.get(2));
		assertTrue(test2.specMap.containsKey(3));
		assertEquals("Gorgo",test2.specMap.get(3));
		assertTrue(test2.specMap.containsKey(4));
		assertEquals("Ponpy",test2.specMap.get(4));
		
		/**Test the intregity of the code of third tree**/
		assertTrue(test3.specMap.containsKey(0));
		assertEquals("Bovin",test3.specMap.get(0));
		assertTrue(test3.specMap.containsKey(1));
		assertEquals("Canfa",test3.specMap.get(1));
		assertTrue(test3.specMap.containsKey(2));
		assertEquals("Macmu",test3.specMap.get(2));
		assertTrue(test3.specMap.containsKey(3));
		assertEquals("Gorgo",test3.specMap.get(3));
		assertTrue(test3.specMap.containsKey(4));
		assertEquals("Ponpy",test3.specMap.get(4));
		
		
		/**Make sure the phylogenetic tree 1 is build correctly**/
		ArrayList<TreeNode> pytTester = traverseTest(test1);
		
		for (int i = 0; i < pytTester.size(); i++){
			if (!pytTester.get(i).is_comm_AnsP)
			assertEquals(pytTester.get(i).taxa, test1.specMap.get(i));
		}
		
		/**Make sure the phylogenetic tree 2 is build correctly**/
		ArrayList<TreeNode> pytTester2 = traverseTest(test2);
		
		for (int i = 0; i < pytTester2.size(); i++){
			if (!pytTester2.get(i).is_comm_AnsP)
			assertEquals(pytTester2.get(i).taxa, test2.specMap.get(i));
		}
		
		/**Make sure the phylogenetic tree 2 is build correctly**/
		ArrayList<TreeNode> pytTester3 = traverseTest(test3);
		
		for (int i = 0; i < pytTester3.size(); i++){
			if (!pytTester3.get(i).is_comm_AnsP)
			assertEquals(pytTester3.get(i).taxa, test3.specMap.get(i));
		}
		
		
		
		
		
					
	}//end of test
	
	public ArrayList<TreeNode> traverseTest(PyTree testTree){
		ArrayList<TreeNode> testNodes = new ArrayList<TreeNode>();
		
		traverseHelper(testTree.root,testNodes);
		
		return testNodes;
	}
	
	public void traverseHelper(TreeNode nodeTester, ArrayList<TreeNode> list){
		if (nodeTester == null)
			return;
		traverseHelper(nodeTester.leftNode,list);
		traverseHelper(nodeTester.rightNode,list);
		if (!list.contains(nodeTester) && !nodeTester.is_comm_AnsP)
		list.add(nodeTester);
		
	}

}//end of PyTreeUnitTest
