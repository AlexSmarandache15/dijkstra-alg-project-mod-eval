#pragma once
#include "Algorithm.h"
#include <vector>

/*
Class used to solve Dijkstra algorithm sequentially.

@author: Alex_Smarandache
*/
class DijkstraSequential : public Algorithm
{
private:
	/**
	 The surce node. Default value: 0;
	 */
	int                           source_node;

	/**
	 The number of nodes.
	 */
	int                           no_of_nodes;

	/**
	 The graph adjacency matrix. Contains int values.
	 */
	std::vector<std::vector<int>> graph_adjacency_matrix;

	/**
	 If a node is already visited, the value of corresponding index will be true, or false otherwise.
	 */
	std::vector<bool>             visited;
    
	/**
	 Contains distances between source node and the other nodes.
	 */
	std::vector<int>              distances;

	/**
	 Description: Calculate current minimum distance.
	 Return: A pair with index of node with minimum distance on first position and the distance on the second position.
	 */
	std::pair<int, int> get_min_distance();

public:
	/**
	Constructor.

	Params:
	graph_adjacency_matrix - a matrix where verticles are on the rows and distances on columns.
	source_node            - The surce node. Default value: 0;
	*/
	DijkstraSequential(const std::vector<std::vector<int>>&, const int& = 0);

	/*
	Used to solve the algorithm.
	*/
	void solve() override;

	/*
	 Return: distances between source and verticles.
	*/
	const std::vector<int>& getDistances();
};

