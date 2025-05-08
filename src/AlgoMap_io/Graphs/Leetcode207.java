package AlgoMap_io.Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi]
indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return true if you can finish all courses. Otherwise, return false.
 */
public class Leetcode207 {
    public static void main(String[] args) {
        System.out.println(canFinish(2,new int[][]{{1,0}}));
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : prerequisites){
            //b를 수강해야 a를 들을 수 있으니까...
            graph[edge[1]].add(edge[0]);
            indegree[edge[0]]++; //진입차수 추가
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int prerequisite = queue.poll();
            for(int i : graph[prerequisite]){
                indegree[i]--;
                if(indegree[i]==0){
                    queue.offer(i);
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            if(indegree[i]!=0){
                return false;
            }
        }
        return true;
    }
}
/*
정말 오랜만에 풀어본 위상정렬 문제라 어려웠다.

선수 조건에 모순이 있어서 false를 리턴한다는 말은, 선수 과목 조건의 관계도를 그래프로 나타냈을 때
사이클이 존재하는지의 유무를 탐색하는 것과 동일하다.

이는 위상정렬 알고리즘으로 풀 수 있는데, 너무 오랜만에 풀어서 당황했다.

위상 정렬 준비물
- ArrayList의 배열로 표현된 그래프
- 연결 차수를 나타내는 indegree 배열
- BFS를 위한 큐 (DFS로 풀 수 있다곤 하는데 잘 모르겠다)
- 각 노드를 탐색 할 때마다 연결된 노드의 indegree 차수를 1씩 감소시킴
- 연결 차수가 0이 되면 큐에 추가 후 탐색...
 */