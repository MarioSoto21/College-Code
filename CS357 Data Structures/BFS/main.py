"""
BFS
Uses Queue

"""
from collections import deque

def bfs(graph, start):
    visited = []
    queue = deque([start])
    while queue:
        print("Visited", visited)
        print("Queue", queue)
        vertex = queue.popleft()
        print(f"\tRemoving {vertex} from the front of the queue")
        if vertex not in visited:
            visited.append(vertex)
            for neighbor in graph[vertex]:
                print(f"{neighbor} is neighbor of {vertex}")
                if neighbor not in visited and neighbor not in queue:
                    queue.extend([neighbor])
def dfs(graph, start):
    visited = []
    queue = deque([start])
    while queue:
        print("Visited", visited)
        print("Queue", queue)
        vertex = queue.popleft()
        print(f"\tRemoving {vertex} from the front of the queue")
        if vertex not in visited:
            visited.append(vertex)
            for neighbor in graph[vertex]:
                print(f"{neighbor} is neighbor of {vertex}")
                if neighbor not in visited and neighbor not in queue:
                    queue.extend([neighbor])
    print(visited)

if __name__ == "__main__":
    graph = {   0:[1,2], 
                1:[2], 
                2:[3], 
                3:[1,2]} # Adjacency list
    bfs(graph,0)

    #(print'*'*50)
    #dgs(graph,0)
