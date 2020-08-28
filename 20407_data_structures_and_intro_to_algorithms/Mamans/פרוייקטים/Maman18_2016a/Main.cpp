/*
  Main.cpp
  20407 Data Structures and Introduction to Algorithms
  Maman 18



  This program contains C++11 features. It will NOT compile in Visual Studio 2010 or earlier.
  Tested in Visual Studio Express 2013
*/
#include <sstream>

#include "datastructures.h"
#include "Customer.h"
#include "BankData.h"

void ShowHelpMessage();
void InputLoop();
void PrintCustomersList(std::string title, LinkedList<Customer *> *list);
std::string GetNthWord(std::string s, std::size_t n);

int main()
{
	InputLoop();
	return 0;
}

void ShowHelpMessage()
{
	std::cout << "Available commands: \n";
	std::cout << "1) Deposit or withdraw:\n";
	std::cout << "\tCUST_NAME CUST_NO AMOUNT\n";
	std::cout << "2) Create customer:\n";
	std::cout << "\t+ CUST_NAME ID_NUM CUST_NO INITIAL_BALANCE\n";
	std::cout << "3) Delete customer:\n";
	std::cout << "\t- CUST_NO\n";
	std::cout << "4) Query customer balance:\n";
	std::cout << "\t? CUST_NO\n";
	std::cout << "5) Query customers with biggest balance:\n";
	std::cout << "\t? MAX\n";
	std::cout << "6) Query customers with negative balance:\n";
	std::cout << "\t? MINUS\n";
	std::cout << "7) Show this help message:\n";
	std::cout << "\t?\n";
	std::cout << "8) Exit:\n";
	std::cout << "\tEXIT\n" << std::endl;
}

void InputLoop()
{
	BankData data;
	ShowHelpMessage();
	std::string lineStr;
	while (true)
	{
		std::cout << "> ";
		// Take input from the user
		getline(std::cin, lineStr);
		// EOF
		if (!std::cin)
			break;

		if (lineStr == "exit" || lineStr == "EXIT" || lineStr == "quit" || lineStr == "QUIT")
			break;

		try
		{
			// Help
			if (GetNthWord(lineStr, 1) == "?" && GetNthWord(lineStr, 2) == "")
			{
				ShowHelpMessage();
			}
			// Get maximum balance
			else if (GetNthWord(lineStr, 1) == "?" && GetNthWord(lineStr, 2) == "MAX" && GetNthWord(lineStr, 3) == "")
			{
				LinkedList<Customer *> *list = data.GetRichest();
				PrintCustomersList("Customers with maximal balance:", list);
			}
			// Get negative balance
			else if (GetNthWord(lineStr, 1) == "?" && GetNthWord(lineStr, 2) == "MINUS" && GetNthWord(lineStr, 3) == "")
			{
				LinkedList<Customer *> *list = data.GetNegative();
				PrintCustomersList("Customers with negative balance:", list);
				delete list;
			}
			// Get balance by customer number
			else if (GetNthWord(lineStr, 1) == "?" && GetNthWord(lineStr, 3) == "")
			{
				long custNumber;
				try
				{
					custNumber = std::stol(GetNthWord(lineStr, 2));
				}
				catch (std::out_of_range)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				catch (std::invalid_argument)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				int balance = data.GetBalanceByNumber(custNumber);
				std::cout << "Balance is " << balance << std::endl;
			}
			// Add customer
			else if (GetNthWord(lineStr, 1) == "+")
			{
				int i = 1;
				while (GetNthWord(lineStr, i + 1) != "")
					i++;
				if (i < 5)
				{
					std::cout << "Error: not enough parameters to create customer" << std::endl;
				}
				else
				{
					std::string name = GetNthWord(lineStr, 2);
					for (int j = 3; j < i - 2; j++)
						name += " " + GetNthWord(lineStr, j);
					std::string idNumber = GetNthWord(lineStr, i - 2);
					long custNumber;
					int balance;
					try
					{
						custNumber = std::stol(GetNthWord(lineStr, i - 1));
					}
					catch (std::out_of_range)
					{
						std::cout << "Error: expected customer number but found something else" << std::endl;
						continue;
					}
					catch (std::invalid_argument)
					{
						std::cout << "Error: expected customer number but found something else" << std::endl;
						continue;
					}
					try
					{
						balance = std::stoi(GetNthWord(lineStr, i));
					}
					catch (std::out_of_range)
					{
						std::cout << "Error: expected balance but found something else" << std::endl;
						continue;
					}
					catch (std::invalid_argument)
					{
						std::cout << "Error: expected balance but found something else" << std::endl;
						continue;
					}

					Customer *newCust = data.NewCustomer(name, idNumber, custNumber, balance);
					std::cout << "New customer create: name = " << newCust->name << " id = " << newCust->idNumber << " customer number = " << newCust->custNumber << " balance = " << newCust->balance << std::endl;
				}
			}
			// Remove customer
			else if (GetNthWord(lineStr, 1) == "-" && GetNthWord(lineStr, 3) == "")
			{
				long custNumber;
				try
				{
					custNumber = std::stol(GetNthWord(lineStr, 2));
				}
				catch (std::out_of_range)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				catch (std::invalid_argument)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				data.RemoveCustomer(custNumber);
				std::cout << "Customer number " << custNumber << " has left the bank." << std::endl;
			}
			// Deposit or withdrawal
			else if (GetNthWord(lineStr, 3) != "")
			{
				int i = 3;
				while (GetNthWord(lineStr, i + 1) != "")
					i++;
				std::string name = GetNthWord(lineStr, 1);
				for (int j = 2; j < i - 1; j++)
					name += " " + GetNthWord(lineStr, j);
				long custNumber;
				int amount;
				try
				{
					custNumber = std::stol(GetNthWord(lineStr, i - 1));
				}
				catch (std::out_of_range)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				catch (std::invalid_argument)
				{
					std::cout << "Error: expected customer number but found something else" << std::endl;
					continue;
				}
				try
				{
					amount = std::stoi(GetNthWord(lineStr, i));
				}
				catch (std::out_of_range)
				{
					std::cout << "Error: expected amount but found something else" << std::endl;
					continue;
				}
				catch (std::invalid_argument)
				{
					std::cout << "Error: expected amount but found something else" << std::endl;
					continue;
				}
				data.DepositOrWithdraw(name, custNumber, amount);
				std::cout << "Customer " << custNumber << " deposited " << amount << std::endl;
			}
			// Unrecognized command
			else
			{
				std::cout << "Unrecognized command" << std::endl;
			}
		}
		catch (std::logic_error e)
		{
			std::cout << "Error: " << e.what() << std::endl;
		}
	}
}

void PrintCustomersList(std::string title, LinkedList<Customer *> *list)
{
	std::cout << title << std::endl;
	LinkedListNode<Customer *> *node = list->getHead();
	while (node != nullptr)
	{
		Customer *customer = node->getKey();
		std::cout << "  Name: " << customer->name << " ID: " << customer->idNumber << " Customer Number: " << customer->custNumber << " Balance: " << customer->balance << std::endl;
		node = node->getNext();
	}
	std::cout << "End of list." << std::endl;
}

std::string GetNthWord(std::string s, std::size_t n)
{
	std::istringstream iss(s);
	while (n-- > 0 && (iss >> s));
	if (iss)
		return s;
	else
		return "";
}
