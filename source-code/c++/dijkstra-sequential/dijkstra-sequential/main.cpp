#include <fstream>
#include <iostream>
#include <chrono>

#include "DijkstraSequential.h"

int main(int argc, char** argv) 
{
	int no_of_nodes;
	int source_node;

	std::ifstream in("input.txt");
	std::ofstream out("output.txt");
    
	in >> no_of_nodes;
	in >> source_node;

	std::vector<std::vector<int>> graph_adjacency_matrix(no_of_nodes);
	std::for_each(std::begin(graph_adjacency_matrix), std::end(graph_adjacency_matrix), [&](auto& iterator) {
		iterator.resize(no_of_nodes);
		for (auto it = std::begin(iterator); it != std::end(iterator); it++) {
			in >> (*it);
		}
	});

	auto start_time = std::chrono::high_resolution_clock::now();
	DijkstraSequential dijkstra_seq(graph_adjacency_matrix, source_node);
	dijkstra_seq.solve();
	std::vector<int> distances = dijkstra_seq.getDistances();
	auto stop_time = std::chrono::high_resolution_clock::now();
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(stop_time - start_time);
	std::cout << "Time: " << duration.count() << " microseconds." << std::endl;

	out << "Vertex \tDistance from " << source_node << "(source node)" << '\n';
	for (int i = 0; i < no_of_nodes; i++) {
		out << i << " \t\t" << distances[i] << '\n';
	}

	return 0;
}