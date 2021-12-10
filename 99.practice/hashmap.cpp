#include<iostream>
#include<vector>
#include<unordered_map>

using namespace std;

void set1(){
unordered_map<string,int> map;
map["IND"] = 1000;
map["USA"] = 100;
map["PK"] = 990;
map["UK"] = 109;
map["NEP"] = 1099;

if(map.find("IND") != map.end())
{
    cout<< map["IND"] << endl;
}
for (auto key:map)
{
    cout << key.first << ":" << key.second << endl;
}
cout<<"------------\n";
for (pair<string,int> key:map)
{
    cout << key.first << ":" << key.second << endl;
}
}

void freqMap(string str)
{
    unordered_map<char,int> map;
    for(char ch: str)map[ch]++;

    for(auto m :map)
    {
        pair<char,int> p = {'e',10};
        // map.insert(p);

        cout<<m.first<<": "<<m.second;
    cout<<endl;
    }
}

void freqMap2(string str)
{
    unordered_map<char,vector<int>> map;
    
    for(int i=0;i<str.length();i++)
    map[str[i]].push_back(i);

    for (auto key:map)
{
    cout << key.first;
    cout<<":";

    for(int d:key.second)
    {
         cout<<d<<" ";
    }
    cout<<endl;
}


}


 

int main()
{
    // freqMap2("SAHITHHHSSSIIAARRTTTTTTAAA");
    freqMap("SAHITHHHSSSIIAARRTTTTTTAAA");
    // set1();
    return 0;
}