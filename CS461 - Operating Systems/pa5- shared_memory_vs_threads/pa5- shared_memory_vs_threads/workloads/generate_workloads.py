import random

def generate_workloads(sizes):
    for size in sizes:
        with open(f"workload_{size}.txt", "w") as f:
            # Generate 'size' random integers (non-zero)
            numbers = [random.randint(1, 10000) for _ in range(size)]
            f.write("\n".join(map(str, numbers)) + "\n")

# Workload sizes as specified
sizes = [2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096]
generate_workloads(sizes)
