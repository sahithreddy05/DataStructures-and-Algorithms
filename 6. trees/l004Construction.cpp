#include<iostream>
#include<vector>
#include<list>


using namespace std;


class TreeNode {
    public:
    int val = 0;
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

    TreeNode(int val)
    {
        this->val = val;
    }
};

TreeNode *getMidNode(TreeNode *head)
{
    if(head == nullptr ||head->right == nullptr)
    return head;

    TreeNode *slow = head , *fast = head;

    while(fast->right != nullptr && fast->right->right != nullptr)
    {
        fast = fast->right->right;
        slow = slow->right;
    }

    return slow;
}

TreeNode *dllToBST(TreeNode *head)
{
    if(head == nullptr|| head->right == nullptr)
    return head;

    TreeNode *midNode  = getMidNode(head);
    TreeNode *prev = midNode->left , *forw = midNode->right;


    midNode->left = midNode->right = forw->left = nullptr;
    if(prev != nullptr)
    prev->right = nullptr;

    TreeNode *root = midNode, *leftHead = (prev != nullptr ? head : nullptr ), *rightHead = forw;
     
     root->left = dllToBST(leftHead);
     root->right = dllToBST(rightHead);

     return root;
}

TreeNode *mergeTwoSortedDLL(TreeNode *l1,TreeNode *l2)
{
    if(l1 == nullptr ||l2 == nullptr)
    return l1 != nullptr ? l1 : l2;

    TreeNode *dummy = new TreeNode(-1);
    TreeNode *prev = dummy, *c1 = l1,*c2 = l2;
    while(c1 != nullptr && c2 !=nullptr)
    {
        if(c1->val <= c2->val)
        {
            prev->right = c1;
            c1->left = prev;

            c1 = c1->right;
        }
        else{
            prev->right = c2;
            c2->left = prev;

            c2 = c2->right;
        }

        if(c1 != nullptr ){
            prev->right = c1;
            c1->left = prev;
        }
        else{
            prev->right = c2;
            c2->left = prev;
        }

        TreeNode *head = dummy->right;
        dummy->right = head->left = nullptr;

        return head;

    }
}
TreeNode *mergeSort(TreeNode *head)
{
    if(head == nullptr || head->right == nullptr)
    return head;

    TreeNode *midNode = getMidNode(head);
    TreeNode *forwHead = midNode->right;
    forwHead->left = midNode->right = nullptr;

    return mergeTwoSortedDLL(mergeSort(head),mergeSort(forwHead));

}

TreeNode *getRightMostNode(TreeNode *node,TreeNode *curr)
{

    while(node->right != nullptr && node->right != curr)
    {
        node = node->right;
    }
    return node;
}

TreeNode *dll(TreeNode *root)
{
    TreeNode *dummy = new TreeNode(-1);
    TreeNode *curr = root, *prev = dummy;

    while(curr != nullptr)
    {
        TreeNode *left = curr->left;

        if(left == nullptr)
        {
            prev->right = curr;
            curr->left = prev;
            prev = prev->right;
        }

        else
        {
            TreeNode *rightMostNode = getRightMostNode(left,curr);
            if(rightMostNode->right == nullptr)
            {   
                rightMostNode->right = curr;
                curr = curr->left;
            }
            else{
                rightMostNode->right = nullptr;

                prev->right = curr;
                curr->left = prev;
                prev = prev->right;

                curr = curr->right;
            }
        }
    } 
}



TreeNode *BTToBST(TreeNode *root)
{
    if(root == nullptr)
    return nullptr;
    TreeNode *head =  dll(root); // binary tree to doubly linked list
    head = mergeSort(head);
    root = dllToBST(head);

    return root;
}