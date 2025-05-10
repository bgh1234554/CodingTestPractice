package AlgoMap_io.Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed).
For example, the first node with val == 1, the second node with val == 2, and so on.
The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph.
Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1.
You must return the copy of the given node as a reference to the cloned graph.
 */
public class Leetcode133 {
    public static void main(String[] args) {

    }
    public Node cloneGraph(Node node) {
        if(node==null){return null;}
        //핵심 아이디어! -> 원본 노드, 복사 노드의 매핑
        //key : OldNode value: NewNode
        Map<Node,Node> map = new HashMap<>();
        Node head = new Node(node.val);
        map.put(node,head);

        //node를 큐에 넣는다
        Queue<Node> q = new LinkedList<>();
        q.offer(node);

        //BFS로 원본 그래프를 탐색하고, 복사본 그래프를 만든다.
        while(!q.isEmpty()){
            Node parent = q.poll();
            for(Node neighbor : parent.neighbors){
                //새로운 노드 발견시, Map 업데이트 후 큐에 추가
                if(!map.containsKey(neighbor)){
                    map.put(neighbor,new Node(neighbor.val));
                    q.offer(neighbor);
                }
                //부모 노드의 복사본에, 자식 노드의 복사본을 이웃으로 추가한다.
                map.get(parent).neighbors.add(map.get(neighbor));
            }
        }

        //헤드 리턴
        return head;
    }
}
/*
그래프 복사 관련 예제 풀 때, 원본 노드 -> 복사본 노드 매핑 구현해야 함
Map<Node, Node> cloneMap = new HashMap<>(); 과 같은 구조를 흔히 사용한다.

한번 만든 복사본을 해시맵에 <원본,복사> 형식으로 저장하고, 다시 만났을 때 재사용, 아니면 새로 만들기

이것이 핵심 아이디어라고 할 수 있다.
 */