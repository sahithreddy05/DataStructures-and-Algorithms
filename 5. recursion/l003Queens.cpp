#include <iostream>
#include <vector>

using namespace std;

// tnb : total no of boxes, tnq : total no queens
int queenCombination(int tnb, int tnq, int bno, int qno, string ans)
{
    if (qno == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = bno; i < tnb; i++)
    {
        count += queenCombination(tnb, tnq, i + 1, qno + 1, ans + "b" + to_string(i) + "q" + to_string(qno) + " ");
    }
    return count;
}

int queenPermutation(vector<bool> &boxes, int tnq, int bno, int qno, string ans)
{
    if (qno == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = bno; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenPermutation(boxes, tnq, 0, qno + 1, ans + "b" + to_string(i) + "q" + to_string(qno) + ' ');
            boxes[i] = false;
        }
    }
    return count;
}


int queenCombination2D(vector<vector<bool>> &board, int tnq, int bno, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
    }

    return count;
}

int queenPermutation2D(vector<vector<bool>> &board, int tnq, int bno, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = 0; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if(!board[r][c])
        {
            board[r][c] = true;
            count += queenPermutation2D(board, tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            board[r][c] = false;
        }
    }

    return count;
}

//=================================================================

bool isSafeToPlaceQueen(vector<vector<bool>> &board,int row,int col)
{   
    // vector<vector<int>> dir = {{0,-1},{-1,-1},{-1,0},{-1,1}};

    // for permutation
    vector<vector<int>> dir = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};

    int n = board.size(), m = board[0].size();
    for(int d = 0 ; d < dir.size();d++)
    {
        for(int rad = 1;rad < board.size();rad++)
        {
            int r = row + rad * dir[d][0];
            int c = col + rad * dir[d][1];

            if( r >= 0 && c >= 0 && r < n && c < m)
            {
                if(board[r][c])
                return false;
            }
            else 
                break;
        }
    }
    return true;
}

int nqueen_01_combi(vector<vector<bool>> &board,int tnq,int idx,string ans)
{   
    if(tnq == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count = 0,n = board.size(),m = board[0].size();
    for(int i =idx;i<n*m;i++)
    {
        int r = i / m;
        int c = i % m;
        if(isSafeToPlaceQueen(board,r,c))
        {
            board[r][c] = true;
            count += nqueen_01_combi(board,tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) +" )");
            board[r][c] = false;
        }
    }
    return count; 
}

int nqueen_01_combi_sub(vector<vector<bool>> &board,int tnq,int idx,string ans)
{   
    int n = board.size(), m = board[0].size();
    if(tnq == 0 || idx == n * m)
    {
        if(tnq == 0)
        {
        cout<<ans<<endl;
        }
        return tnq == 0 ? 1 : 0;
    }

    int count = 0;
        int r = idx / m;
        int c = idx % m;
        if(isSafeToPlaceQueen(board,r,c))
        {
            board[r][c] = true;
            count += nqueen_01_combi_sub(board,tnq - 1, idx + 1, ans + "(" + to_string(r) + "," + to_string(c) +" )");
            board[r][c] = false;
        }
        count += nqueen_01_combi_sub(board,tnq, idx + 1, ans);


    return count; 
}
int nqueen_01_permu(vector<vector<bool>> &board,int tnq,int idx,string ans)
{   
    if(tnq == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count = 0,n = board.size(),m = board[0].size();
    for(int i =idx;i<n*m;i++)
    {
        int r = i / m;
        int c = i % m;
        if(isSafeToPlaceQueen(board,r,c) && !board[r][c])
        {
            board[r][c] = true;
            count += nqueen_01_permu(board,tnq - 1, 0, ans + "(" + to_string(r) + "," + to_string(c) +" )");
            board[r][c] = false;
        }
    }
    return count; 
}

vector<bool> row; // boolean[] row
vector<bool> col;
vector<bool> diag;
vector<bool> aDiag;

int nqueen_02_combi(int n,int m,int tnq,int idx,string ans)
{
    if(tnq == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count  = 0;
    for(int i=idx;i < n*m;i++)
    {
        int r = i/m;
        int c = i%m;
        if(!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1])
        {
            row[r] = col[c] = diag[r+c] = aDiag[r-c+m-1] = true;
            count += nqueen_02_combi(n,m,tnq - 1,i+1,ans + "(" + to_string(r) + "," + to_string(c) + ")");
            row[r] = col[c] = diag[r+c] = aDiag[r-c+m-1] = false;
        }
    }
    return count;
}

int nqueen_02_permu(int n,int m,int tnq,int idx,string ans)
{
    if(tnq == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count  = 0;
    for(int i=idx;i < n*m;i++)
    {
        int r = i/m;
        int c = i%m;
        if(!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1])
        {
            row[r] = col[c] = diag[r+c] = aDiag[r-c+m-1] = true;
            count += nqueen_02_permu(n,m,tnq - 1,0,ans + "(" + to_string(r) + "," + to_string(c) + ")");
            row[r] = col[c] = diag[r+c] = aDiag[r-c+m-1] = false;
        }
    }
    return count;
}




void nQueen() {
     int n = 4, m = 4;
    vector<vector<bool>> board(4,vector<bool>(4,false));
    int tnq = 4;

    // cout<<nqueen_01_combi(board,tnq,0,"")<<endl;
    // cout<<nqueen_01_permu(board,tnq,0,"")<<endl;
    // cout<<nqueen_01_combi_sub(board,tnq,0,"")<<endl;

    row.resize(n, false); // row = new boolean[n];
    col.resize(m, false);
    diag.resize(n + m - 1, false);
    aDiag.resize(n + m - 1, false);

    // cout << nqueen_02_combi(n, m, tnq, 0, "") << endl;
    cout<<nqueen_02_permu(n,m,tnq,0,"")<<endl;
}

void queencombination()
{
    int tnb = 5, tnq = 3;
    // vector<bool> boxes(tnb, false);
    // cout<<queenCombination(tnb,tnq,0,0,"")<<endl;
    // cout << queenPermutation(boxes, tnq, 0, 0, "") << endl;
    // vector<vector<bool>> board(4, vector<bool>(4, false));
    // cout << queenCombination2D(board, 4, 0, "") << endl;
    // cout<<queenPermutation2D(board,4,0,"")<<endl;
}
int main()
{
    // queencombination();
    nQueen();
}