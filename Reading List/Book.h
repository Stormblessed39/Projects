#pragma once
#include <string>
using namespace std;

class Book {
public:
	Book();
	Book(string title, string author, bool finished);
	string getTitle();
	string getAuthor();
	bool isFinished();
	void setAuthor(string author);
	void setFinished(bool finished);
	void setTitle(string title);
	string format();


private:
	string title;
	string author;
	bool finished;

};