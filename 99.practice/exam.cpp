#include<iostream>
using namespace std;
 

 bool checkPerfectNumber(int num) {
        int sum = 0;
        if(num==1)return false;
        for(int i=1;i*i<=num;i++){
            if(num%i==0){
                sum+=i+num/i;
            }
        }
        return sum==num*2;
    }

int main()
{
 
    int n;
    cin>>n;
   if(checkPerfectNumber(n)) cout<<"Perfect";
   else cout<<"Not Perfect";
    return 0;
}