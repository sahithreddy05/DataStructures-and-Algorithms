#include<iostream>
#include<vector>

using namespace std;

int permutation(string str,string ans)
{
    if(str.length() == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count = 0;
    for(int i=0;i<str.size();i++)
    {
        char ch = str[i];
        string ros = str.substr(0,i) + str.substr(i + 1);
        count += permutation(ros,ans + ch);
    }
    return count;

}

int permutationunique(string str,string ans)
{
    if(str.length() == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    vector<bool> vis(26,false);
    int count = 0;
    for(int i=0;i< str.length();i++)
    {
        char ch = str[i];
        if(!vis[ch - 'a'])
        {
            vis[ch - 'a'] = true;
            string ros = str.substr(0,i) + str.substr(i+1);
            count += permutationunique(ros,ans + ch); 
        }
    }
    return count;
} 

int permuatationunique2(string str, string ans)
{
    if(str.length() == 0)
    {
        cout<< ans << endl;
        return 1;
    }

    char prev = '$';
    int count = 0;
    for(int i=0;i< str.length();i++)
    {
        char ch = str[i];
        if(prev != ch)
        {
            string ros = str.substr(0,i) + str.substr(i+1);
            count += permuatationunique2(ros,ans + ch);
        }
        prev = ch;
    }

    return count;
    
}

int main() {

    // cout<<permutation("aabb","")<<endl;
    cout<<permuatationunique2("aabb","")<<endl;

    return 0;
}
