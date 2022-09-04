#include <bits/stdc++.h>
using namespace std;

int main() {
    string f, i1, o, d, m, y;
    int  n, sum = 0, fsum;
    set <char> a;
    cin >> n;
    cin.ignore(256, '\n');
    for (int i = 0; i < n; i++) {
        getline(cin, f, ',');
        getline(cin, i1, ',');
        getline(cin, o, ',');
        getline(cin, d, ',');
        getline(cin, m, ',');
        getline(cin, y);
        for (int j = 0; j < f.length(); j++)
            a.insert(f[j]);
        for (int j = 0; j < i1.length(); j++)
            a.insert(i1[j]);
        for (int j = 0; j < o.length(); j++)
            a.insert(o[j]);
        for (int j = 0; j < d.length(); j++)
            sum += d[j] - '0';
        for (int j = 0; j < m.length(); j++)
            sum += m[j] - '0';
        fsum = sum * 64 + a.size() + (26 - ('z' - tolower(f[0]))) * 256;
        printf("%X ", fsum & 0xfff);
        sum = 0;
        a.clear();
    }
    return 0;
}

