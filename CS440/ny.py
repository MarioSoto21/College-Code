import simpy
import random

class Packet:
    def __init__(self, source, destination, size):
        self.source = source
        self.destination = destination
        self.size = size

class Node:
    def __init__(self, env, node_id):
        self.env = env
        self.node_id = node_id
        self.input_queue = simpy.Store(env)
        self.output_queues = {}
        self.routing_table = {}  # Node's routing table (can be precomputed)

    def send_packet(self, packet):
        destination_node_id = packet.destination
        if destination_node_id in self.output_queues:
            yield self.output_queues[destination_node_id].put(packet)
        else:
            print(f"Error: No route from Node {self.node_id} to Node {destination_node_id}")

    def process_input_queue(self):
        while True:
            packet = yield self.input_queue.get()
            # Simulate routing (you may use the routing table)
            next_hop = self.routing_table.get(packet.destination)
            if next_hop is not None:
                yield self.send_packet(packet)
            else:
                print(f"Error: No route for destination {packet.destination} at Node {self.node_id}")

def packet_generator(env, node, destination, packet_size):
    while True:
        yield env.timeout(random.expovariate(0.5))  # Exponential inter-arrival time
        packet = Packet(node.node_id, destination, packet_size)
        yield env.process(node.send_packet(packet))

def network_simulation(env, network_graph, traffic_pairs):
    nodes = {node_id: Node(env, node_id) for node_id in network_graph.nodes()}

    for edge in network_graph.edges():
        nodes[edge[0]].output_queues[edge[1]] = nodes[edge[1]].input_queue

    for source, destination in traffic_pairs:
        packet_size = random.uniform(0, 1)
        env.process(packet_generator(env, nodes[source], destination, packet_size))

    for node in nodes.values():
        env.process(node.process_input_queue())

# Usage
seed_value = 123
simulator = NetworkSimulator(seed_value)
simulator.load_graph()
simulator.generate_traffic()

env = simpy.Environment()
env.process(network_simulation(env, simulator.graph, simulator.traffic_pairs))
env.run(until=1000)  # Run the simulation for 1000 time units
