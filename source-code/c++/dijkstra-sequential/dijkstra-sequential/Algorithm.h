#pragma once

#define INFINITE 1000000
#include <math.h>
#include <algorithm>

/*
Abstract representaction for an algorithm. 

@author: Alex_Smarandache
*/
class Algorithm
{
public:
	 virtual void solve() = 0;
};

