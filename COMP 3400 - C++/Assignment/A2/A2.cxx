#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {
	vector<string> words;
	string input;

	while (cin >> input) {
		words.push_back(input);
	}

	auto pos_1 = find(words.begin(), words.end(), "begin");
	auto pos_2 = find(words.begin(), words.end(), "end");
	vector<string> output(words.size());

	if (pos_1 != words.end()) {
		++pos_1;
		copy(pos_1, pos_2, output.begin());
	}

	for (auto i = output.begin(); i != output.end(); i++) {
		cout << *i << " ";
	}

	cout << "\n";
	return 0;
}