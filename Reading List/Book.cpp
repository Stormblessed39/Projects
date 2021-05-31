#include <iostream>
#include <string>
#include <iomanip>
#include "Book.h"
using namespace std;


Book::Book() {
	title = "";
	author = "";
	finished = false;
}

Book::Book(string title, string author, bool finished) {
	this->title = title;
	this->author = author;
	this->finished = finished;
}

void Book::setAuthor(string author) {
	this->author = author;
}

void Book::setTitle(string title) {
	this->author = author;
}

void Book::setFinished(bool finished) {
	this->finished = finished;
}

string Book::getTitle()
{
	return title;
}

string Book::getAuthor() {
	return author;
}

bool Book::isFinished() {
	return finished;
}

string Book::format() {
	string complete = "No";
	if (finished == true) {
		complete = "Yes";
	}

	return "|" + title + "|" + author + "|" + complete + "|\n";
}
