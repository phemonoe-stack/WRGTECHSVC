#include <iostream>
#include <cctype>
#include <string>

using namespace std;

void addCard(char entry, int& hand);
void addRoyal(char entry, int& hand);
void displayHand(int hand);

int main() {
    int hand = 0;
    int cards = 0;
    char ans = 'y';

    cout << "Do you want to total your Blackjack score? (y/n): ";
    cin >> ans;

    while (ans == 'y' || ans == 'Y') {
        hand = 0;

        cout << "Enter the number of cards in your hand: ";
        cin >> cards;

        for (int i = 1; i <= cards; i++) {
            char entry;
            cout << "Enter card #" << i << " (A K Q J T 9 8 7 6 5 4 3 2): ";
            cin >> entry;

            entry = std::tolower(entry);

            if (std::isalpha(entry)) {
                addRoyal(entry, hand);
            } else if (std::isdigit(entry)) {
                addCard(entry, hand);
            }
        }

        displayHand(hand);

        cout << "\nPlay again? (y/n): ";
        cin >> ans;
    }

    cout << "\nGoodbye!\n";
    return 0;
}

void addRoyal(char entry, int& hand) {
    switch (entry) {
        case 'a': {
            char choice;
            cout << "Ace high (H) or low (L)? ";
            cin >> choice;
            choice = std::tolower(choice);
            hand += (choice == 'h') ? 11 : 1;
            break;
        }
        case 'k':
        case 'q':
        case 'j':
        case 't':
            hand += 10;
            break;
    }
}

void addCard(char entry, int& hand) {
    int value = entry - '0';  // convert char digit → int
    if (value >= 2 && value <= 9) {
        hand += value;
    }
}

void displayHand(int hand) {
    cout << "\nYour hand equals: " << hand << "\n";

    if (hand == 21) {
        cout << "Blackjack — you're a winner!\n";
    } else if (hand > 21) {
        cout << "You've busted!\n";
    }

    cout << "Thanks for using Blackjack!\n";
}
