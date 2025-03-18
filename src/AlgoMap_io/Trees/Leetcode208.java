package AlgoMap_io.Trees;
/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store
and retrieve keys in a dataset of strings.

There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class Leetcode208 {
    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("abc");
        boolean bparam = obj.search("abc");
        boolean bparam2 = obj.startsWith("ab");
    }
    //이전 문제 참고해서 만든 풀이 - 실행시간 34ms
    static class Trie{
        public Trie[] next = new Trie[26];
        public boolean isEnd=false;

        public Trie(){

        }
        public void insert(String word){
            int len = word.length();
            Trie cur = this;
            for(int i=0; i<len; i++){
                char c = word.charAt(i);
                if(cur.next[c-'a']==null){
                    cur.next[c-'a'] = new Trie();
                }
                cur = cur.next[c-'a'];
                if(i==len-1){
                    cur.isEnd=true;
                }
            }
        }
        public boolean search(String word){
            Trie cur = this;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if(cur.next[c-'a']==null){
                    return false;
                }
                cur = cur.next[c-'a'];
            }
            return cur.isEnd;
        }
        public boolean startsWith(String prefix){
            Trie cur = this;
            for(int i=0; i<prefix.length(); i++){
                char c = prefix.charAt(i);
                if(cur.next[c-'a']==null){
                    return false;
                }
                cur = cur.next[c-'a'];
            }
            return true;
        }
    }
}
/*
참고 코드 - 백준 14425번
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tNode root = new tNode(); //문자열 저장할 트리 생성
        while(N>0){
            String text = br.readLine();
            tNode now = root; //루트 트리에서부터 차근차근 시작하기.
            for(int i=0;i<text.length();i++){
                char c = text.charAt(i);
                //아직 액세스되지 않았으면 새롭게 노드 만들기
                if(now.next[c-'a']==null){
                    now.next[c-'a']=new tNode();
                }
                //이후 자식 노드로 접근하기
                now = now.next[c-'a'];
                //맨 끝에 도달하면 끝이라고 알려주도록 isEnd 값을 true로 만들기
                if(i==text.length()-1){
                    now.isEnd=true;
                }
            }
            N--;
        }
        int count = 0;
        while(M>0){
            String text = br.readLine();
            //루트 노드 탐색
            tNode now = root;
            for(int i=0;i<text.length();i++){
                char c = text.charAt(i);
                //없는 문자가 존재하면 더 이상 이 String을 탐색할 필요가 없으니 break
                if(now.next[c-'a']==null) break;
                now=now.next[c-'a'];
                //끝까지 다 탐색했으면 count++
                if(i==text.length()-1&&now.isEnd){
                    count++;
                }
            }
            M--;
        }
        System.out.println(count);
    }
    static class tNode{
        public tNode[] next = new tNode[26]; //알파벳이 26개니까.
        public boolean isEnd;
    }
 */