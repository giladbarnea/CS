/*
  Customer.cpp
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#include "Customer.h"


Customer::Customer(std::string name, std::string idNumber, long custNumber)
{
	this->name = name;
	this->idNumber = idNumber;
	this->custNumber = custNumber;
	this->balance = 0;
}


Customer::~Customer()
{
}
