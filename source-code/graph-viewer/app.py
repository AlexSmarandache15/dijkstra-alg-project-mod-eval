import pygame
import random
import math

def parse_input(data):
    lines = data.strip().split("\n")
    no_of_nodes, source_node = map(int, lines[0].split())
    adjacency_matrix = []

    for line in lines[1:]:
        row = list(map(int, line.split()))
        adjacency_matrix.append(row)

    return no_of_nodes, source_node, adjacency_matrix

def draw_graph(screen, positions, adjacency_matrix, no_of_nodes):
    # Colors
    white = (255, 255, 255)
    black = (0, 0, 0)
    red = (255, 0, 0)
    green = (0, 255, 0)
    blue = (0, 0, 255)

    # Clear screen
    screen.fill(white)

    # Draw edges with costs
    for i in range(no_of_nodes):
        for j in range(no_of_nodes):
            if adjacency_matrix[i][j] != 1000000 and adjacency_matrix[i][j] != 0:
                start_pos = positions[i]
                end_pos = positions[j]
                pygame.draw.line(screen, black, start_pos, end_pos, 1)

                # Calculate midpoint for displaying cost
                mid_x = (start_pos[0] + end_pos[0]) // 2
                mid_y = (start_pos[1] + end_pos[1]) // 2

                font = pygame.font.SysFont(None, 20)
                cost_text = font.render(str(adjacency_matrix[i][j]), True, blue)
                screen.blit(cost_text, (mid_x, mid_y))

    # Draw nodes
    for i, pos in enumerate(positions):
        pygame.draw.circle(screen, green, pos, 20)
        font = pygame.font.SysFont(None, 24)
        text = font.render(str(i), True, red)
        screen.blit(text, (pos[0] - 10, pos[1] - 10))

    pygame.display.flip()

def generate_random_positions(no_of_nodes, width, height):
    positions = []
    margin = 50

    for _ in range(no_of_nodes):
        x = random.randint(margin, width - margin)
        y = random.randint(margin, height - margin)
        positions.append((x, y))

    return positions

def main():
    # Read input data from file
    with open("input.txt", "r") as file:
        data = file.read()

    # Parse input
    no_of_nodes, source_node, adjacency_matrix = parse_input(data)

    # Pygame setup
    pygame.init()
    width, height = 800, 600
    screen = pygame.display.set_mode((width, height))
    pygame.display.set_caption("Graph Visualization")

    # Generate random positions for the nodes
    positions = generate_random_positions(no_of_nodes, width, height)

    # Main loop
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        draw_graph(screen, positions, adjacency_matrix, no_of_nodes)

    pygame.quit()

if __name__ == "__main__":
    main()
