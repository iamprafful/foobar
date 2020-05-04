from itertools import permutations

def solution(times, time_limit):
    if len(times) <= 2:
        return []
    graph = matrix2graph(times)
    return bellman_ford(times, graph, time_limit, "Start")
def powerset(list):
    x = len(list)
    masks = [1 << i for i in range(x)]
    for i in range(1 << x):
        yield [ss for mask, ss in zip(masks, list) if i & mask]
def getneighbourindex(neighbour, graphsize):
    if neighbour == "Bulkhead":
        return graphsize - 1
    elif neighbour == "Start":
        return 0
    else:
        return int(neighbour)+1
def matrix2graph(matrix):
    keys = ["Start"]
    for num in range(1, len(matrix)-1):
        keys.append(num-1)
    keys.append("Bulkhead")
    graph = dict(zip(keys, matrix))
    return graph
def initialize(graph, source):
    distance = {}
    predecessor = {}
    for node in graph:
        distance[node] = 1000
        predecessor[node] = None
    distance[source] = 0
    return distance, predecessor
def relax(node, neighbour, graph, distance, predecessor):
    nidx = getneighbourindex(neighbour, len(graph))
    if distance[node] + graph[node][nidx] < distance[neighbour]:
        distance[neighbour] = distance[node] + graph[node][nidx]
        predecessor[neighbour] = node
def bellman_ford(matrix, graph, time_limit, source):
    dist, pred = initialize(graph, source)
    for num in range(len(graph)-1):
        for node in graph:
            temp = dict(graph)
            del temp[node]
            for neighbour in temp:
                relax(node, neighbour, graph, dist, pred)
    for node in graph:
        for neighbour in graph:
            nidx = getneighbourindex(neighbour, len(graph))
            if dist[node] + graph[node][nidx] < dist[neighbour]:
                return [num for num in range(0, len(graph)-2)]
    spaths = floyd(matrix)
    return find_most_bunnies(matrix, spaths, time_limit)
def floyd(matrix):
    n = len(matrix)
    spaths = deepcopy(matrix)
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if spaths[i][k] + spaths[k][j] < spaths[i][j]:
                    spaths[i][j] = spaths[i][k] + spaths[k][j]
    return spaths
def find_most_bunnies(matrix, spaths, time_limit):
    n = len(matrix)-2
    bunnyids = []
    for num in range(n):
        bunnyids.append(num)
    pset = powerset(bunnyids)
    pset = sorted(pset)
    optimal_bunnies = []
    for sub in pset:
        for permutation in permutations(sub):
            subsum = 0
            prev = 0
            next = len(matrix)-1
            for bunnyid in permutation:
                next = bunnyid+1
                subsum += spaths[prev][next]
                prev = next
            subsum += spaths[prev][len(matrix)-1]
            if subsum <= time_limit and len(sub) > len(optimal_bunnies):
                optimal_bunnies = sub
                if len(optimal_bunnies) == n:
                    break
            else:
                pass
    return optimal_bunnies
