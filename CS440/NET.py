import random
import networkx as nx
import simpy

def generate_connected_graph(num_nodes):
    while True:
        graph = nx.erdos_renyi_graph(num_nodes, p=0.2)
        if nx.is_connected(graph):
            break

    return graph

def save_graph_to_file(graph, filename):
    with open(filename, 'w') as file:
        file.write(f"{graph.number_of_nodes()} {graph.number_of_edges()}\n")
        for edge in graph.edges():
            file.write(f"{edge[0]} {edge[1]}\n")

class Packet:
    def __init__(self, source, destination, size):
        self.source = source
        self.destination = destination
        self.size = size
        self.start_time = None

class Node:
    def __init__(self, env, node_id, graph, service_rate):
        self.env = env
        self.node_id = node_id
        self.graph = graph
        self.service_rate = service_rate
        self.input_queue = simpy.Store(env)
        self.output_queues = {}

    def service_packet(self, packet):
        # Service time follows an exponential distribution with rate service_rate
        service_time = random.expovariate(self.service_rate)
        yield self.env.timeout(service_time)

    def send_packet(self, packet):
        destination_node_id = packet.destination

        # Set the start time when the packet is sent
        packet.start_time = self.env.now

        simulator.update_statistics(success=True, transmission_time=0)

        if destination_node_id in self.output_queues:
            yield self.output_queues[destination_node_id].put(packet)
        else:
            print(f"Error: No direct route from Node {self.node_id} to Node {destination_node_id}. Trying alternative routes.")

            # Find alternative routes
            try:
                alternative_paths = nx.shortest_path(self.graph, source=self.node_id, target=destination_node_id, method='dijkstra')
                if len(alternative_paths) > 1:
                    chosen_path = random.choice(alternative_paths[1:])
                    if isinstance(chosen_path, list):
                        next_hop = chosen_path[1]
                    else:
                        next_hop = chosen_path  # Handle the case where chosen_path is an integer
                    if next_hop in self.output_queues:
                        yield self.output_queues[next_hop].put(packet)
                        print(f"Node {self.node_id} forwarded packet to Node {next_hop} using an alternative route.")
                        return
            except nx.NetworkXNoPath:
                pass

            # If no alternative route is found, print an error message
            print(f"Error: No route from Node {self.node_id} to Node {destination_node_id} using any available routes.")

    def process_input_queue(self):
        while True:
            packet = yield self.input_queue.get()
            print(f"Node {self.node_id} received a packet from Node {packet.source} with size {packet.size}")
            path = nx.shortest_path(self.graph, source=self.node_id, target=packet.destination)
            next_hop = path[1]

            if next_hop in self.output_queues:
                yield self.output_queues[next_hop].put(packet)
                print(f"Node {self.node_id} forwarded packet to Node {next_hop}")

                # Service the packet
                yield self.service_packet(packet)
                
            else:
                print(f"Error: No route from Node {self.node_id} to Node {packet.destination}")

# Usage
num_nodes = 150
graph = generate_connected_graph(num_nodes)
save_graph_to_file(graph, 'network_graph.txt')

class NetworkSimulator:
    
    def __init__(self, seed, service_rate, graph_filename=None):
            self.seed = seed
            random.seed(self.seed)
            self.graph_filename = graph_filename
            self.env = simpy.Environment()
            self.nodes = {}  # Leave this empty, it will be populated later
            self.service_rate = service_rate  # Add service_rate as a class attribute
            self.packet_count = 0
            self.successful_transmissions = 0
            self.failed_transmissions = 0
            self.transmission_times = []

    def load_graph(self):
        # Load the graph from the file or generate a new one if not provided
        if self.graph_filename:
            self.graph = nx.read_adjlist(self.graph_filename)
        else:
            num_nodes = 150
            self.graph = generate_connected_graph(num_nodes)
    def generate_traffic(self, service_rate):
        if not hasattr(self, 'graph'):
            self.load_graph()

        # Randomly select 20 source-destination pairs
        num_pairs = 20
        self.nodes = {node_id: Node(self.env, node_id, self.graph, service_rate) for node_id in self.graph.nodes()}
        self.traffic_pairs = random.sample(list(self.nodes.keys()), k=num_pairs * 2)
        self.traffic_pairs = [(self.traffic_pairs[i], self.traffic_pairs[i + 1]) for i in range(0, len(self.traffic_pairs), 2)]

        for source, destination in self.traffic_pairs:
            packet_size = random.uniform(0, 1)
            packet = Packet(source, destination, packet_size)
            self.packet_count += 1
            self.env.process(self.nodes[source].send_packet(packet))

    def run_simulation(self, end_time):
        if not hasattr(self, 'graph'):
            self.load_graph()
        if not hasattr(self, 'traffic_pairs'):
            self.generate_traffic()

        # Connect nodes based on the edges in the network graph
        for edge in self.graph.edges():
            self.nodes[edge[0]].output_queues[edge[1]] = self.nodes[edge[1]].input_queue

        # Generate traffic between source-destination pairs
        for source, destination in self.traffic_pairs:
            packet_size = random.uniform(0, 1)
            packet = Packet(source, destination, packet_size)
            self.packet_count += 1
            self.env.process(self.nodes[source].send_packet(packet))

        self.env.run(until=end_time)

    def setup_simulation(self):
        if not hasattr(self, 'graph'):
            self.load_graph()

        # Connect nodes based on the edges in the network graph
        for edge in self.graph.edges():
            self.nodes[edge[0]].output_queues[edge[1]] = self.nodes[edge[1]].input_queue

    def simulate_traffic(self, end_time):
        if not hasattr(self, 'traffic_pairs'):
            self.generate_traffic()

        # Generate traffic between source-destination pairs
        for source, destination in self.traffic_pairs:
            packet_size = random.uniform(0, 1)
            packet = Packet(source, destination, packet_size)
            self.packet_count += 1
            self.env.process(self.nodes[source].send_packet(packet))

        self.env.run(until=end_time)
        
    def gather_statistics(self):
        total_packets_generated = sum(len(node.output_queues) for node in self.nodes.values())
        total_packets_reached_destination = sum(len(node.input_queue.items) for node in self.nodes.values())
        total_transmission_time = 0
        max_completion_time = float('-inf')
        min_completion_time = float('inf')
        total_packets_dropped = total_packets_generated - total_packets_reached_destination

        print("\nNode-wise Statistics:")
        for node_id, node in self.nodes.items():
            print(f"Node {node_id} sent {len(node.output_queues)} packets.")
            print(f"Node {node_id} successfully received {len(node.input_queue.items)} packets.")
            print(f"Node {node_id} dropped {len(node.output_queues) - len(node.input_queue.items)} packets.")
            
            if len(node.input_queue.items) > 0:
                average_transmission_time = sum(self.transmission_times) / len(node.input_queue.items)
                print(f"Node {node_id} average packet transmission time: {average_transmission_time:.2f} time units\n")

                total_transmission_time += sum(self.transmission_times)

                if self.transmission_times:  # Check if the list is not empty
                    max_completion_time = max(max_completion_time, max(self.transmission_times))
                    min_completion_time = min(min_completion_time, min(self.transmission_times))
            else:
                print(f"Node {node_id} did not successfully receive any packets.\n")


        print("\nOverall Statistics:")
        print(f"Total packets generated: {total_packets_generated}")
        print(f"Total packets successfully received: {total_packets_reached_destination}")
        
        if total_packets_generated > 0:
            success_rate = (total_packets_reached_destination / total_packets_generated) * 100
            print(f"Percentage of successfully received packets: {success_rate:.2f}%")
        else:
            print("No packets were generated.")
        
        if total_packets_reached_destination > 0:
            average_packet_transmission_time = total_transmission_time / total_packets_reached_destination
            print(f"Average packet transmission time: {average_packet_transmission_time:.2f} time units")
            print(f"Maximum completion time for transmissions: {max_completion_time:.2f} time units")
            print(f"Minimum completion time for transmissions: {min_completion_time:.2f} time units")
        else:
            print("No packets reached their destination.")
        
        print(f"Average number of packets dropped at a router: {total_packets_dropped / len(self.nodes):.2f}")

    def update_statistics(self, success, transmission_time):
        if success:
            self.successful_transmissions += 1
        else:
            self.failed_transmissions += 1

        self.transmission_times.append(transmission_time)

# Usage
# Usage
seed_value = 32  # Replace with your desired seed value
service_rate_value = 1.0  # Replace with your desired service_rate value
simulator = NetworkSimulator(seed_value, graph_filename='network.txt', service_rate=service_rate_value)
simulator.load_graph()
simulator.generate_traffic(service_rate=service_rate_value)
simulator.run_simulation(1000)
simulator.gather_statistics()
