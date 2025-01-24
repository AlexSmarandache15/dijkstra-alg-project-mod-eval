#include "DijkstraSequential.h"
#include <stddef.h>
#include <ratio>
#include <execution>

DijkstraSequential::DijkstraSequential(const std::vector<std::vector<int>>& graph_adjacency_matrix,
	const int& source_node)
{
	this->source_node = source_node;
	this->graph_adjacency_matrix = graph_adjacency_matrix;
	no_of_nodes = graph_adjacency_matrix.size();
	visited.resize(no_of_nodes, false);
	distances.resize(no_of_nodes, INFINITE);
}

std::pair<int, int> DijkstraSequential::get_min_distance()
{
	std::pair<int, int> min_distance;
	min_distance.first  = 0;
	min_distance.second = INFINITE;
	for (int i = 0; i < no_of_nodes; i++) {
		if (!visited[i] && distances[i] <= min_distance.second) {
			min_distance.first  = i;
			min_distance.second = distances[i];
		}
	}

	return min_distance;
}

void DijkstraSequential::solve()
{
	std::current_exception::
	distances[source_node] = 0;
	for (int loop_index = 0; loop_index < no_of_nodes - 1; loop_index++) {
		const std::pair<int, int> min_distance = get_min_distance();
		visited[min_distance.first] = true;
		for (int i = 0; i < no_of_nodes; i++) {
			if (!visited[i] && graph_adjacency_matrix[min_distance.first][i]
				&& distances[min_distance.first] != INFINITE) {
				distances[i] = std::min(distances[min_distance.first] + graph_adjacency_matrix[min_distance.first][i],
					distances[i]);
			}
		}
	}
}

const std::vector<int>& DijkstraSequential::getDistances()
{
	return distances;
}
