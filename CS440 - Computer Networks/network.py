import random
import networkx as nx
import numpy as np 

def generate_connected_graph(num_nodes):
    while True:
        graph = nx.erdos_renyi_graph(num_nodes, p=0.2)
        if nx.is_connected(graph):
            return graph

def write_graph_to_file(graph, filename):
    with open(filename, 'w') as file:
        num_nodes = graph.number_of_nodes()
        num_edges = graph.number_of_edges()

        file.write(f"{num_nodes} {num_edges}\n")
        for edge in graph.edges():
            file.write(f"{edge[0]} {edge[1]}\n")
    
# Example usage
graph = generate_connected_graph(150)
write_graph_to_file(graph, 'network.txt')

import heapq
import math

class Event:
    def __init__(self, time, action):
        self.time = time
        self.action = action

    def __lt__(self, other):
        return self.time < other.time

class Packet:
    def __init__(self, source, destination, size):
        self.source = source
        self.destination = destination
        self.size = size

class Router:
    def __init__(self):
        self.input_queues = []
        self.output_queues = []

    def process_packet(self, packet):
        if not self.queue:
            transmission_start_time = current_time
            transmission_time = packet.size  # Simple transmission time calculation
            transmission_end_time = current_time + transmission_time

            # Process the packet transmission
            print(f"Node {self.node_id}: Transmitting Packet {packet.packet_id} to Node {packet.destination}")

            # Assuming simple packet transmission, update the current time
            current_time = transmission_end_time

            # Check if the packet reached its destination
            if self.node_id == packet.destination:
                print(f"Node {self.node_id}: Packet {packet.packet_id} reached its destination")

            return transmission_end_time

        else:
            # Queue the packet and it will be transmitted later
            self.queue.append(packet)
            return None

class NetworkSimulator:
    def __init__(self, graph, seed):
        self.current_time = 0
        self.event_queue = []
        self.nodes = {}
        self.seed = seed
        random.seed(seed)
        self.total_packets_generated = 0
        self.successful_packets = 0
        self.transmission_times = []
        self.completion_times = []
        self.dropped_packets = []

        for node in graph.nodes():
            self.nodes[node] = Router()

    def schedule_event(self, time, action):
        event = Event(time, action)
        heapq.heappush(self.event_queue, event)

    def generate_packets(self, lambd=0.5):
        # Generate packets based on Poisson distribution for inter-arrival times
        inter_arrival_times = np.random.exponential(1 / lambd, size=20)

        for i, time in enumerate(np.cumsum(inter_arrival_times)):
            source = random.choice(list(self.nodes.keys()))
            destination = random.choice(list(self.nodes.keys()))
            size = random.uniform(0, 1)  # Uniform distribution for packet sizes
            packet = Packet(source, destination, size)

            # Schedule packet transmission event
            self.schedule_event(time, lambda p=packet: self.transmit_packet(p))

    def transmit_packet(self, packet):
        source_router = self.nodes[packet.source]
        # source_router.process_packet(packet)
        transmission_start_time = self.current_time

        # Process packet transmission and calculate transmission time
        source_router.process_packet(packet)
        transmission_end_time = self.current_time
        transmission_time = transmission_end_time - transmission_start_time

        # Update statistics
        self.transmission_times.append(transmission_time)

        # Schedule completion event
        completion_time = transmission_end_time + random.uniform(1, 10)  # Uniform distribution for completion times
        self.schedule_event(completion_time, lambda p=packet: self.complete_transmission(p, transmission_start_time))

    def complete_transmission(self, packet, transmission_start_time):
        destination_router = self.nodes[packet.destination]

        # Process packet completion and calculate completion time
        destination_router.process_packet(packet)
        completion_time = self.current_time
        completion_time = completion_time - transmission_start_time

        # Update statistics
        self.completion_times.append(completion_time)

        # Check if the packet reached its destination successfully
        if packet.destination == packet.source:
            self.successful_packets += 1

    def run(self, end_time):
        while self.current_time < end_time:
            current_event = heapq.heappop(self.event_queue)
            self.current_time = current_event.time
            current_event.action()
        print(f"Total Packets Generated: {self.total_packets_generated}")
        print(f"Successful Packets: {self.successful_packets}")
        print(f"Percentage of Successfully Received Packets: {self.successful_packets / self.total_packets_generated * 100}%")
        print(f"Average Packet Transmission Time: {np.mean(self.transmission_times)} seconds")
        print(f"Maximum Packet Transmission Time: {np.max(self.transmission_times)} seconds")
        print(f"Minimum Packet Transmission Time: {np.min(self.transmission_times)} seconds")
        print(f"Average Completion Time: {np.mean(self.completion_times)} seconds")
        print(f"Maximum Completion Time: {np.max(self.completion_times)} seconds")
        print(f"Minimum Completion Time: {np.min(self.completion_times)} seconds")

# Example usage for Part 2
def main():
    # Read graph from file or generate a connected graph
    graph_filename = 'network.txt'
    try:
        graph = nx.read_adjlist(graph_filename)
    except FileNotFoundError:
        graph = generate_connected_graph(150)
        write_graph_to_file(graph, graph_filename)

    seed = 321  # Use a seed value as specified in the prompt
    simulator = NetworkSimulator(graph, seed)

    # Generate source-destination pairs and run simulation
    for _ in range(20):
        source = random.choice(list(graph.nodes()))
        destination = random.choice(list(graph.nodes()))
        simulator.generate_packets()  # Implement this function to generate packets

    simulator.run(1000)  # Run simulation for 1000 seconds
    # Print and log statistics as required

if __name__ == "__main__":
    main()


# import networkx as nx
# import random
# import heapq
# import math

# class Event:
#     def __init__(self, time, action):
#         self.time = time
#         self.action = action

#     def __lt__(self, other):
#         return self.time < other.time

# class Packet:
#     def __init__(self, source, destination, size):
#         self.source = source
#         self.destination = destination
#         self.size = size

# class Router:
#     def __init__(self):
#         self.input_queues = []
#         self.output_queues = []

#     def process_packet(self, packet):
#         # Process packet based on FCFS, calculate transmission time, etc.
#         pass

# class NetworkSimulator:
#     def __init__(self, graph, seed):
#         self.current_time = 0
#         self.event_queue = []
#         self.nodes = {}
#         self.seed = seed
#         random.seed(seed)

#         for node in graph.nodes():
#             self.nodes[node] = Router()

#     def schedule_event(self, time, action):
#         event = Event(time, action)
#         heapq.heappush(self.event_queue, event)

#     def generate_packets(self):
#         # Generate packets based on Poisson distribution and uniform size distribution
#         pass

#     def run(self, end_time):
#         while self.current_time < end_time:
#             current_event = heapq.heappop(self.event_queue)
#             self.current_time = current_event.time
#             current_event.action()

# # Part 1: Generate Network Graph and Check Connectivity
# def generate_connected_graph(num_nodes):
#     while True:
#         graph = nx.erdos_renyi_graph(num_nodes, p=0.2)  # Adjust p based on connectivity requirements
#         if nx.is_connected(graph):
#             return graph

# # Part 2: Network Simulator
# def main():
#     # Read graph from file or generate a connected graph
#     num_nodes = 150
#     graph_filename = 'network.txt'
#     try:
#         graph = nx.read_adjlist(graph_filename)
#     except FileNotFoundError:
#         graph = generate_connected_graph(num_nodes)
#         nx.write_adjlist(graph, graph_filename)

#     seed = 123  # Use a seed value as specified in the prompt
#     simulator = NetworkSimulator(graph, seed)

#     # Generate source-destination pairs and run simulation
#     for _ in range(20):
#         source = random.choice(list(graph.nodes()))
#         destination = random.choice(list(graph.nodes()))
#         simulator.generate_packets()  # Implement this function to generate packets

#     simulator.run(1000)  # Run simulation for 1000 seconds
#     # Print and log statistics as required

# if __name__ == "__main__":
#     main()
