#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include "Book.h"
using namespace std;


int main() {
	
	// The variable to write the books to a file
	ofstream file;

	// The title of the book
	string title;

	// The author of the book
	string author;

	// The line taken from the file
	string line;

	// If the book is finished or not
	bool finished;

	// The y/n character checking to see if the user finished the book or not
	char fin;

	// The user's menu choice
	int choice = 0;

	//How many books the user has read
	int count = 0;

	while (choice != 3) {
		// Print the menu and get the user's choice
		cout << "\nMenu\n(1)Add a book\n(2)Print list\n(3)Exit\n";
		cin >> choice;
		
		if (choice == 1) {

			// Ask for the book info
			file.open("List.txt", ios::app);
			cout << "Enter book title: ";
			cin.ignore();
			getline(cin, title);
			cout << "\nEnter book author: ";
			getline(cin, author);
			cout << "\nEnter if book is finished(y/n): ";
			cin >> fin;

			
			if (fin == 'y') {
				finished = true;
			}
			else {
				finished = false;
			}

			// Create a book object from the data entered
			Book book(title, author, finished);

			// Format the info and add it to the file
			file << book.format();
			file.close();
		}

		if (choice == 2) {
			ifstream is("List.txt");

			// Print the top bar
			cout << setw(15) << "Title " << "         ";
			cout << setw(15) << "Author " << "        ";
			cout << setw(10) << "Completed " << endl;
			cout << "_________________________________________________________________________________\n" << endl;

			// Get, format, and print the info from the file
			while (getline(is, line)) {
				
				line.replace(line.find('|'), 1, "");
				title = line.substr(0, line.find('|'));
				line.replace(0, line.find('|')+1, "");
				author = line.substr(0, line.find('|'));
				line.replace(0, line.find('|') + 1, "");
				line.replace(line.find('|'), 1, "");


				cout << setw(20) << title << "  ";
				cout << setw(20) << author << "  ";
				cout << setw(10) << line << endl;


				count++;
			}
			// Print the total books the user has in the file
			cout << "\nTotal books: " << count << endl;
		}
		
	}

	
}