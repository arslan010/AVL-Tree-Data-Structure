//package avl_presentation;
import java.util.*;
public class AVL_Presentation {

    public static void main(String[] args) {

        Scanner read1=new Scanner(System.in);
        AVLTREE read=new AVLTREE();
        System.out.println("****************************************************");
        System.out.println("<:   AVL Tree Data Structure Presentation   :>");
        System.out.println("<:       "
                + "Adelson Velski and Landis   :>");
        System.out.println("____________________________________________________");
        char ch;
        int choice;
        System.out.println("____________________________________________________");
        do
        {
            System.out.println("\n         TREE Operations");
            System.out.println("________________________________________________");
            System.out.println("     Enter 1 to Insert Value in Tree:");
            System.out.println("     Enter 2 to Search Value in Tree:");
            System.out.println("     Enter 3 to Count Nodes in Given Tree:");
            System.out.println("     Enter 4 to Check Empty Nodes:");
            System.out.println("     Enter 5 to Clear Tree OR Delete Values:");
            System.out.println("________________________________________________\n");
            choice=read1.nextInt();

            if(choice==1){
                System.out.println("Enter Value to Insert in Tree :");
                read.Insert(read1.nextInt());
                }
            else if(choice==2){
                System.out.println("Enter Value to Search in Tree :");
                System.out.println("Value is : "+ read.SearchNode(read1.nextInt()));
                }
            else if(choice==3){
                System.out.println("Nodes are : "+ read.CountNodes());
                }
            else if(choice==4){
                System.out.println("Empty Nodes are : "+ read.Empty());
                }
            else if(choice==5){
                System.out.println("\n: Tree Cleared :");
                read.MakeEmpty();
                }
            else
            {
                System.out.println("Wrong Choice Try Again :\n ");
            }
            System.out.println("____________________________________________________");
            System.out.print("\nValue in Post Order : ");
            read.Postorder();
            System.out.print("\nValue in Pre Order : ");
            read.Preorder();
            System.out.print("\nValue in In Order : ");
            read.Inorder();
            System.out.println("\n____________________________________________________\n");
            System.out.println("Do You Want To Continue Y for Yes and N for No \n");
            ch=read1.next().charAt(0);
        }
        while (ch=='Y'|| ch=='y');
    }

}
class AVLNode
 {
     AVLNode left, right;
     int data;
     int height;
     AVLNode()
     {
         left=null;
         right=null;
         data=0;
         height=0;
     }
     AVLNode(int n)
     {
         left=null;
         right=null;
         data=n;
         height=0;
     }
 }
 class AVLTREE
 {
     AVLNode root;
      AVLTREE()
     {
         root=null;
     }
     boolean Empty()
     {
         return root==null;
     }
     void MakeEmpty()
     {
         root=null;
     }
     void Insert(int data)
     {
         root=Insert(data, root);
     }
     int height(AVLNode t )
     {
         return t==null ? -1 : t.height;
     }
     int max(int lhs, int rhs)
     {
         return lhs > rhs ? lhs : rhs;
     }
 AVLNode Insert(int x, AVLNode t)
     {
         if (t==null)
             t=new AVLNode(x);
         else if (x < t.data)
         {
             t.left=Insert( x, t.left );
             if( height( t.left ) - height( t.right ) == 2 )
                 if( x < t.left.data )
                     t=RotateWithLeftChild( t );
                 else
                     t=DoubleWithLeftChild( t );
         }
         else if( x > t.data )
         {
             t.right=Insert( x, t.right );
             if( height( t.right ) - height( t.left )==2 )
                 if( x > t.right.data)
                     t=RotateWithRightChild( t );
                 else
                     t=DoubleWithRightChild( t );
         }
         else
           ;
         t.height=max( height( t.left ), height( t.right ) ) + 1;
         return t;
     }

     AVLNode RotateWithLeftChild(AVLNode n2)
     {
         AVLNode n1=n2.left;
         n2.left=n1.right;
         n1.right=n2;
         n2.height=max( height( n2.left ), height( n2.right ) ) + 1;
         n1.height=max( height( n1.left ), n2.height ) + 1;
         return n1;
     }


     AVLNode RotateWithRightChild(AVLNode n1)
     {
         AVLNode n2=n1.right;
         n1.right=n2.left;
         n2.left=n1;
         n1.height=max( height( n1.left ), height( n1.right ) ) + 1;
         n2.height=max( height( n2.right ), n1.height ) + 1;
         return n2;
     }

      AVLNode DoubleWithLeftChild(AVLNode n3)
     {
         n3.left = RotateWithRightChild( n3.left );
         return RotateWithLeftChild( n3 );
     }

     AVLNode DoubleWithRightChild(AVLNode n1)
     {
         n1.right=RotateWithLeftChild( n1.right );
         return RotateWithRightChild( n1 );
     }

     int CountNodes()
     {
         return CountNodes(root);
     }
     int CountNodes(AVLNode r)
     {
         if (r==null)
             return 0;
         else
         {
             int l = 1;
             l += CountNodes(r.left);
             l += CountNodes(r.right);
             return l;
         }
     }
     boolean SearchNode(int val)
     {
         return SearchNode(root, val);
     }
     boolean SearchNode(AVLNode r, int val)
     {
         boolean found=false;
         while ((r!=null) && !found)
         {
             int rval=r.data;
             if (val<rval)
                 r=r.left;
             else if (val>rval)
                 r=r.right;
             else
             {
                 found=true;
                 break;
             }
             found=SearchNode(r, val);
         }
         return found;
     }

     void Inorder()
     {
         Inorder(root);
     }
     void Inorder(AVLNode r)
     {
         if(r!=null)
         {
             Inorder(r.left);
             System.out.print(r.data +" ");
             Inorder(r.right);
         }
     }
     void Preorder()
     {
         Preorder(root);
     }
     void Preorder(AVLNode r)
     {
         if(r!=null)
         {
             System.out.print(r.data +" ");
             Preorder(r.left);
             Preorder(r.right);
         }
     }
     void Postorder()
     {
         Postorder(root);
     }
     void Postorder(AVLNode r)
     {
         if(r!=null)
         {
             Postorder(r.left);
             Postorder(r.right);
             System.out.print(r.data +" ");
         }
     }
 }