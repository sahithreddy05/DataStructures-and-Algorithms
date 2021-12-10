#include<iostream>
#include<vector>
#include<list>

using namespace std;

class TreeNode{
    public:
        int val = 0;
        TreeNode *left = nullptr;
        TreeNode *right = nullptr;

        TreeNode(int val)
        {
            this->val = val;
        }
};

TreeNode *getRightFirstNode(TreeNode *node,TreeNode* curr)
{
    while(node->right != nullptr && node->right != curr)
    {
        node = node->right;
    }
    return node;
}

