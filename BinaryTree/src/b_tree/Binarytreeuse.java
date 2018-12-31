package b_tree;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class Binarytreeuse {
	
	//take input levelwise
	public static Binarytreenode<Integer> inputLevel()
	{
		Scanner s=new Scanner(System.in);
		int rootdata;
		System.out.println("Enter the root data: ");
		rootdata=s.nextInt();
		if(rootdata==-1)
		{
			return null;
		}
		Binarytreenode<Integer> root=new Binarytreenode<>(rootdata);
		QueueLL<Binarytreenode<Integer>> q=new QueueLL<>();
		q.enqueue(root);
		while(!q.isEmpty())
		{
			Binarytreenode<Integer> current;
			try {
				current=q.dequeue();
			} catch (QueueEmptyException1 e) {
				// TODO Auto-generated catch block
				return null;
			}
			System.out.println("Enter left child of "+current.data);
			int leftchild=s.nextInt();
			if(leftchild!=-1)
			{
				Binarytreenode<Integer> lchild=new Binarytreenode<>(leftchild);
				q.enqueue(lchild);
				current.left=lchild;
			}
			System.out.println("Enter right child of "+current.data);
			int rightchild=s.nextInt();
			if(rightchild!=-1)
			{
				Binarytreenode<Integer> rchild=new Binarytreenode<>(rightchild);
				q.enqueue(rchild);
				current.right=rchild;
			}
		}
		
		return root;
		
	}
	
	//print btree using recursion
	public static void print(Binarytreenode<Integer> root)
	{
		if(root==null)
			return;
		String toBeprinted=root.data+": ";
		if(root.left!=null)
		{
			toBeprinted+="L"+root.left.data+",";
		}
		if(root.right!=null)
		{
			toBeprinted+="R"+root.right.data;
		}
		System.out.println(toBeprinted);
		print(root.left);
		print(root.right);
	}
	
	//print btree levelwise
	public static void printlevel(Binarytreenode<Integer> root)
	{
		QueueLL<Binarytreenode<Integer>> q=new QueueLL<>();
		q.enqueue(root);
		while(!q.isEmpty())
		{
			try {
				Binarytreenode<Integer> current=q.dequeue();
				System.out.print(current.data+":");
				if(current.left!=null)
				{
					q.enqueue(current.left);
					System.out.print("L"+current.left.data);
				}
				if(current.right!=null)
				{
					q.enqueue(current.right);
					System.out.print(",R"+current.right.data);
				}
				System.out.println();
			} catch (QueueEmptyException1 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//take input using recursion for btree
	public static Binarytreenode<Integer> takeinput(Scanner s)
	{
		int rootdata;
		System.out.println("Enter root data: ");
		rootdata=s.nextInt();
		if(rootdata==-1)
		{
			return null;
		}
		Binarytreenode<Integer> root=new Binarytreenode<>(rootdata);
		root.left=takeinput(s);
		root.right=takeinput(s);
		return root;
	}
	
	//function to find height
	public static int height(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			return 0;
		}
		int curr_height=1;
		int l_height=height(root.left);
		int r_height=height(root.right);
		curr_height+=Math.max(l_height, r_height);
		return curr_height;
	}
	
	//function to fing diameter of btree
	public static int diameter(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			return 0;
		}
		int c_dia=height(root.left)+height(root.right);
		int l_dia=diameter(root.left);
		int r_dia=diameter(root.right);
		int result=Math.max(c_dia,Math.max(l_dia,r_dia));
		return result;
	}
	
	//better approach to find diameter of btree
	public static Pair<Integer,Integer> heightDiameter (Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			Pair<Integer,Integer> output=new Pair<>();
			output.height=0;
			output.diameter=0;
			return output;
		}
		
		Pair<Integer,Integer> lhd=heightDiameter(root.left);
		Pair<Integer,Integer> rhd=heightDiameter(root.right);
		int height=1+Math.max(lhd.height, rhd.height);
		int op1=lhd.height+rhd.height;
		int op2=lhd.diameter;
		int op3=rhd.diameter;
		int diameter=Math.max(op1, Math.max(op2, op3));
		Pair<Integer,Integer> output=new Pair<>();
		output.height=height;
		output.diameter=diameter;
		return output;
		
	}
	
	//function to mirror btree
	public static void mirror(Binarytreenode<Integer> root)
	{
		if(root==null){
			return;
		}
		Binarytreenode<Integer> temp=root.left;
		root.left=root.right;
		root.right=temp;
		
		mirror(root.left);
		mirror(root.right);
	}
	
	//Check if a binary tree is BST
	public static Triplet<Boolean,Integer,Integer> isBST(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			Triplet<Boolean,Integer,Integer> output=new Triplet<>();
			output.first=true;
			output.second=Integer.MAX_VALUE;
			output.third=Integer.MIN_VALUE;
			return output;
		}
		
		Triplet<Boolean,Integer,Integer> leftop=isBST(root.left);
		Triplet<Boolean,Integer,Integer> rightop=isBST(root.right);
		
		int min=Math.min(root.data, Math.min(leftop.second, rightop.second));
		int max=Math.max(root.data, Math.max(leftop.third, rightop.third));
		
		boolean isBST2=(leftop.first&&
				(root.data>leftop.third&&root.data<rightop.second)
				&&rightop.first);
		
		Triplet<Boolean,Integer,Integer> output=new Triplet<>();
		output.first=isBST2;
		output.second=min;
		output.third=max;
		return output;
	}
	
	public static boolean isBST3(Binarytreenode<Integer> root,int min, int max)
	{
		if(root==null)
		{
			return true;
		}
		
		if(root.data<min||root.data>max)
			return false;
		
		boolean isleftBST=isBST3(root.left,min,root.data-1);
		boolean isrightBST=isBST3(root.right,root.data,max);
		
		return isleftBST && isrightBST;
	}
	
	//function to check if node is present or not
	public static boolean findNode(Binarytreenode<Integer> root,int x)
	{
		if(root==null)
		{
			return false;
		}
		boolean ans=false;
		if(root.data==x)
		{
			ans=true;
		}
		boolean l_ans=findNode(root.left,x);
		boolean r_ans=findNode(root.right,x);
		return (ans||(l_ans||r_ans));
	}
	
	//inOder traversal of btree
	public static void inOrder(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			return;
		}
		inOrder(root.left);
		System.out.print(root.data+" ");
		inOrder(root.right);
	}
	
	//preOder traversal of btree
		public static void preOrder(Binarytreenode<Integer> root)
		{
			if(root==null)
			{
				return;
			}
			System.out.print(root.data+" ");
			preOrder(root.left);
			preOrder(root.right);
		}
		
		//postOder traversal of btree
		public static void postOrder(Binarytreenode<Integer> root)
		{
			if(root==null)
			{
				return;
			}
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data+" ");
		}
		
		
	//make b-tree using inorder & preorder traversals 
	public static Binarytreenode<Integer> premakeTree(int[] pre,int[] in,int preS,int preE,int inS,int inE)
	  {
	    
	    if(inS>inE)
	    {
	      return null;
	    }
	    int rootdata=pre[preS];
	    Binarytreenode<Integer> root=new Binarytreenode<>(rootdata);
	    int i=inS;
	    while(i<=inE)
	    {
	      if(in[i]==rootdata)
	      {
	        break;
	      }
	      i++;
	    }
	    
	    int linS=inS;
	    int linE=i-1;
	    int lpreS=preS+1;
	    int lpreE=preS+(linE-linS+1);
	    
	    int rinS=i+1;
	    int rinE=inE;
	    int rpreS=lpreE+1;
	    int rpreE=preE;
	    
	    root.left=premakeTree(pre,in,lpreS,lpreE,linS,linE);
	    root.right=premakeTree(pre,in,rpreS,rpreE,rinS,rinE);
	    
	    return root;
	  }
	  
	//make b-tree using postorder and inorder traversals
	public static Binarytreenode<Integer> postmakeTree(int[] post, int[] in,int postS, int postE,int inS,int inE)
	  {
	    if(inS>inE)
	    {
	      return null;
	    }
	    
	    int rdata=post[postE];
	    Binarytreenode<Integer> root=new Binarytreenode<>(rdata);
	    int i=inS;
	    while(i<=inE)
	    {
	      if(in[i]==rdata)
	        break;
	      i++;
	    }
	    
	    int linS=inS;
	    int linE=i-1;
	    int lpostS=postS;
	    int lpostE=postS+(linE-linS);;
	    
	    int rinS=i+1;
	    int rinE=inE;
	    int rpostS=lpostE+1;
	    int rpostE=postE-1;
	    
	    root.left=postmakeTree(post,in,lpostS,lpostE,linS,linE);
	    root.right=postmakeTree(post,in,rpostS,rpostE,rinS,rinE);
	    
	    return root;
	  }
	
	//Sum of all nodes
	public static int sum(Binarytreenode<Integer> root){
      if(root==null)
      {
        return 0;
      }
      
      int sum=root.data;
      sum+=sum(root.left);
      sum+=sum(root.right);
      
      return sum;
	}
	
	//Check if btree is balanced
	public static Pair2<Boolean,Integer> heightBalanced(Binarytreenode<Integer> root)
	  {
	    if(root==null)
	    {
	      Pair2<Boolean,Integer> output=new Pair2<>();
	      output.isBalanced=true;
	      output.height=0;
	      return output;
	    }
	    
	    Pair2<Boolean,Integer> lhB=heightBalanced(root.left);
	    Pair2<Boolean,Integer> rhB=heightBalanced(root.right);
	    int height=1+Math.max(lhB.height,rhB.height);
	    if(Math.abs(lhB.height-rhB.height)>1)
	    {
	      Pair2<Boolean,Integer> output=new Pair2<>();
	      output.isBalanced=false;
	      output.height=height;
	      return output;
	    }
	    
	    boolean lans=lhB.isBalanced;
	    boolean rans=rhB.isBalanced;
	    
	    boolean ans=lans&&rans;
	    
	    Pair2<Boolean,Integer> output=new Pair2<>();
	      output.isBalanced=ans;
	      output.height=height;
	      return output;
	  }
	
	//Level order traversal	
	public static void printLevelWise(Binarytreenode<Integer> root){
      QueueLL<Binarytreenode<Integer>> pending=new QueueLL<>();
      pending.enqueue(root);
      pending.enqueue(null);
      while(!pending.isEmpty())
      {
    	  try
    	  {
          Binarytreenode<Integer> current=pending.dequeue();
          if(current!=null)
          {
            System.out.print(current.data+" ");
            if(current.left!=null)
            {
            	pending.enqueue(current.left);
            }
            if(current.right!=null)
            {
            	pending.enqueue(current.right);
            }
          }
          else
          {
            System.out.println();
            if(pending.size()!=0)
            pending.enqueue(null);
          }
          
          
        }
        catch(QueueEmptyException1 e)
        {
        	e.printStackTrace();
        }}
      }
	
	//remove leaf node
	public static Binarytreenode<Integer> removeAllLeaves(Binarytreenode<Integer> root){
		
      if(root==null)
      {
        return null;
      }
      
      if(root.left==null&&root.right==null)
      {
        root=null;
        return root;
      }
      
      root.left=removeAllLeaves(root.left);
      root.right=removeAllLeaves(root.right);
      
      return root;
      
	}
	
	//Level wise LinkedList
	public static ArrayList<Node<Binarytreenode<Integer>>> LLForEachLevel(Binarytreenode<Integer> root) {
		
		// Write your code here
      
      QueueLL<Binarytreenode<Integer>> q=new QueueLL<>();
      q.enqueue(root);
      q.enqueue(null);
      Node<Binarytreenode<Integer>> head=null,tail=null;
      ArrayList<Node<Binarytreenode<Integer>>> output=new ArrayList<>();
      
      while(!q.isEmpty())
      {
        try
        {
          Binarytreenode<Integer> current=q.dequeue();
          if(current!=null)
          {
            Node<Binarytreenode<Integer>> ll=new Node<>(current);
            if(head==null)
            {
              head=ll;
              tail=ll;
            }
            else
            {
              tail.next=ll;
              tail=tail.next;
            }
            if(current.left!=null)
              q.enqueue(current.left);
            if(current.right!=null)
              q.enqueue(current.right);
          }
          if(current==null)
          {
            
            output.add(head);
            head=null;
            tail=null;
            if(q.size()!=0)
            {
              q.enqueue(null);
            }
          }
          
        }
        catch(QueueEmptyException1 e)
        {
          
        }
      }
      
      return output;

	}
	
	//Print tree in spiral form(zigzag)
	public static void printZigZag(Binarytreenode<Integer> root) {
		
        if(root==null)
        {
        	return;
        }
        
        Stack<Binarytreenode<Integer>> s1=new Stack<>();
        Stack<Binarytreenode<Integer>> s2=new Stack<>();
        
        s1.push(root);
        
        while(!s1.isEmpty()||!s2.isEmpty())
        {
          
          while(!s2.isEmpty())
            {
            	Binarytreenode<Integer> temp=s2.peek();
                s2.pop();
                System.out.print(temp.data+" ");
            
            	if(temp.right!=null)
                s1.push(temp.right);
                
                if(temp.left!=null)
                s1.push(temp.left);
                
                
            }
          System.out.println();
        	while(!s1.isEmpty())
            {
            	Binarytreenode<Integer> temp=s1.peek();
                s1.pop();
                System.out.print(temp.data+" ");
                
                if(temp.left!=null)
                s2.push(temp.left);
                
                if(temp.right!=null)
                s2.push(temp.right);
            }
            
            System.out.println();
            
            
        }
	}
	
	//nodes without sibling
	public static void printNodesWithoutSibling(Binarytreenode<Integer> root) {
		
      if(root==null)
      {
        return;
      }
      
      if((root.left==null&&root.right!=null)||(root.left!=null&&root.right==null))
      {
        if(root.left!=null)
        {
          System.out.println(root.left.data);
        }
        else
         System.out.println(root.right.data); 
      }
      
      printNodesWithoutSibling(root.left);
      printNodesWithoutSibling(root.right);

	}
	
	//Sorted array to balanced BST
	public static Binarytreenode<Integer> SArray2BST(int[] arr, int si, int ei)
    {
      if(si>ei)
      {
        return null;
      }
      
      int mid=(si+ei)/2;
      
      Binarytreenode<Integer> root=new Binarytreenode<>(arr[mid]);
      root.left=SArray2BST(arr,si,mid-1);
      root.right=SArray2BST(arr,mid+1,ei);
      
      return root;
    }
	
	//find root to node path
	public static ArrayList<Integer> root2node(Binarytreenode<Integer> root,int k)
	{
		if(root==null)
		{
			return null;
		}
		
		if(root.data==k)
		{
			ArrayList<Integer> op=new ArrayList<>();
			op.add(root.data);
			return op;
		}
		ArrayList<Integer> lo=root2node(root.left,k);
		if(lo!=null)
		{
			lo.add(root.data);
			return lo;
		}
		
		ArrayList<Integer> ro=root2node(root.right,k);
		if(ro!=null)
		{
			ro.add(root.data);
			return ro;
		}
		
		else
			return null;
	}
	
	//BST to linked list
	private static Pair3<LinkedListNode<Integer>,LinkedListNode<Integer>> Bst2ll(Binarytreenode<Integer> root)
	  {
	    if(root==null)
	    {
	      Pair3<LinkedListNode<Integer>,LinkedListNode<Integer>> output =new Pair3<>();
	      output.first=null;
	      output.second=null;
	      return output;
	    }
	    
	    Pair3<LinkedListNode<Integer>,LinkedListNode<Integer>> leftll=Bst2ll(root.left);
	    LinkedListNode<Integer> middlell=new LinkedListNode<>(root.data);
	    Pair3<LinkedListNode<Integer>,LinkedListNode<Integer>> rightll=Bst2ll(root.right);
	    
	    Pair3<LinkedListNode<Integer>,LinkedListNode<Integer>> output =new Pair3<>();
	    if(leftll.first!=null)
	    {
	      output.first=leftll.first;
	      output.second=leftll.second;
	      output.second.next=middlell;
	      output.second=output.second.next;
	      if(rightll.first==null)
	      {
	        return output;
	      }
	    }
	    if(leftll.first!=null&&rightll.first!=null)
	    {
	      output.second.next=rightll.first;
	      output.second=rightll.second;
	      return output;
	    }
	    if(leftll.first==null&&rightll.first!=null)
	    {
	      output.first=middlell;
	      middlell.next=rightll.first;
	      output.second=rightll.second;
	      return output;
	    }
	    else
	    {
	      output.first=middlell;
	      output.second=middlell;
	      return output;
	      
	    }
	    
	    
	  }


	public static void main(String[] args) {
		
		//Scanner s=new Scanner(System.in);
		//Binarytreenode<Integer> root=takeinput(s);
	    //print(root);
		Binarytreenode<Integer> root=inputLevel();
		
		printLevelWise(root);
		//print(root);
		//printlevel(root);
		//System.out.println("Diameter of tree: "+ diameter(root));
		//System.out.println("Diameter of tree: "+ heightDiameter(root).diameter+" Height of tree: "+ heightDiameter(root).height);
		/*int size = s.nextInt();
		int[] pre = new int[size];
		for(int i = 0; i < size; i++){
			pre[i] = s.nextInt();
		}
		int in[] = new int[size];
		for(int i = 0; i < size; i++){
			in[i] = s.nextInt();
		}
		Binarytreenode<Integer> root = premakeTree(pre,in,0,pre.length-1,0,in.length-1);
		printlevel(root);*/
		
		/*ArrayList<Node<Binarytreenode<Integer>>> output = LLForEachLevel(root);
		for(Node<Binarytreenode<Integer>> head : output){
			Node<Binarytreenode<Integer>> temp = head;
			while(temp != null){
				System.out.print(temp.data.data + " ") ;
				temp = temp.next;
			}
			System.out.println();
		}*/
		
		

	}

}
