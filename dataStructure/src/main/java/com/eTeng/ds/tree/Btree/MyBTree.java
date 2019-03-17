package com.eTeng.ds.tree.Btree;

/**
 * BTree的实现
 *  BTree的约束：
 *  1.数据项都在树叶上。
 *  2.非叶子节点存储(M-1)个关键字以及搜索得方向,并且关键字i代表i+1子树的最小关键字。
 *  3.树的根或者树叶最少有2-M个儿子数。
 *  4.除了根和叶子节点外所有的节点都至少有M/2-M个儿子数。
 *  5.所有的树叶都在同一个深度(高度),并且每个树叶的数据项为L/2-L。
 *
 * 部分实现参考: 1.https://github.com/tclxspy/Articles/blob/master/algorithm/Code/BTree.java
 *             2.数据结构与算法分析java语言描述第三版
 * @FileName MyBTree.java
 * @Author eTeng
 * @Date 2019/3/14 14:09
 * @Description
 */
public class MyBTree<Key extends Comparable<? super Key>,Value> implements BTree<Key,Value>{

    /**
     * 树的度
     */
    private static  final int DEGREE = 20;

    /**
     * 树高度
     */
    private int height;

    /**
     * 根节点
     */
    private Node<Key,Value> root;

    /**
     * 树的节点数
     */
    private int elementCount;

    public MyBTree(){
        root = new Node(0);
    }

    public int size(){
        return this.elementCount;
    }

    public int height(){
        return this.height;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Value get(Key key){
        Entry<Key,Value> target = search(root,key,height);
        return  target != null ? target.val : null;
    }

    public void add(Key key,Value val){

        if(key == null){
            throw new IllegalArgumentException("key must not be null");
        }
        // 分裂新的节点
        Node<Key,Value> newNode = insert(root,key,val,height);
        // 树元素累加
        elementCount++;
        if(newNode == null){
            return ;
        }
        Node newRoot = new Node(2);
        // 重新新建根节点
        newRoot.childs[0] = new Entry<Key,Value>(root.childs[0].key,null,root);
        newRoot.childs[1] = new Entry<Key,Value>(newNode.childs[0].key,null,newNode);
        height++;
        root = newRoot;
    }

    public void set(Key key,Value val){
        Entry<Key,Value> target = search(root,key,height);
        if(target != null){
            target.val = val;
        }
    }

    public void del(Key key){
        if(key == null){
            throw new IllegalArgumentException("key must no be null");
        }
        Node<Key,Value> newRoot = remove(root,key,height);
        if(newRoot == null || newRoot.equals(root)){
            return;
        }
        // 产生新的根
        root = newRoot;
        height--;
    }

    /**
     * 查找元素
     * @param currNode 当前元素
     * @param key 关键字
     * @return
     */
    private Entry<Key,Value> search(Node<Key,Value> currNode,Key key,int height){
        Entry<Key,Value> [] childs = currNode.childs;
        // 叶子节点
        if(height == 0){
            for(int i = 0; i < currNode.count; i++){
                // 找到元素
                if(childs[i].key.compareTo(key) == 0){
                    return childs[i];
                }
            }
        }else{
            // 树枝节点(内部节点)
            int posistion = posistion(currNode,key);
            return (Entry<Key,Value>)search(childs[posistion].next,key,height - 1);
        }
        return null;
    }

    /**
     * 添加节点
     * @param currNode 当前节点
     * @param key 键
     * @param val 值
     * @param height 树高度
     * @return
     */
    private Node<Key,Value> insert(Node<Key,Value> currNode,Key key,Value val,int height){

        // 在节点关键字列表位置
        int pos;
        // 新增节点
        Entry nodeData = new Entry(key,val,null);

        // 外部节点(树叶)
        if(height == 0){
            // 定位
            pos = pos(currNode,key);
        }else{
            // 内部节点(树枝)
            // 定位
            pos = posistion(currNode,key);
            // 产生新的右节点
            Node<String,Integer> newNode = insert(currNode.childs[pos++].next,key,val,height-1);
            // 没有节点分裂
            if(newNode == null){
                return null;
            }
            // 发生节点分裂,并插入一个新的父节点的孩子
            nodeData.key = newNode.childs[0].key;
            nodeData.next = newNode;
            // 给父节点插入指引节点
        }
        //插入节点
        for(int i = currNode.count; i > pos; i--){
            currNode.childs[i] = currNode.childs[i-1];
        }
        currNode.childs[pos] = nodeData;
        currNode.count++;
        // 度没有超出限制
        if(currNode.count < DEGREE){
            return  null;
        }
        // 分裂节点
        return split(currNode);
    }

    public Node<Key,Value> remove(Node<Key,Value> currNode,Key key,int height){
        // 位置
        int pos;
        // 外部节点(树叶)
        if(height == 0){
            pos = pos(currNode,key);
        }else{
            // 内部节点(树枝)
            pos = posistion(currNode,key);
            Node<Key,Value> mergeNode = remove(currNode.childs[pos].next,key,height-1);
            if(mergeNode == null){
                return null;
            }
            // 更新key
            currNode.childs[pos].key = mergeNode.childs[0].key;
            // 节点没变化,没发生合并
            if(mergeNode.equals(currNode)){
                return null;
            }
        }
        // 删除节点和领养孩子
        if(currNode.count + currNode.previous.count > DEGREE){
            // 集合从pos开始，向前移动一位
            for(int i = pos; i<currNode.count; i++){
                currNode.childs[i] = currNode.childs[i+1];
            }
            // 当前节点孩子数-1
            currNode.count--;
            // 领养
            if(currNode.count <= DEGREE / 2){
                // 集合从最后一位开始，向后移动一位
                for(int i = currNode.count; i > 0; i--){
                    currNode.childs[i] = currNode.childs[i-1];
                }
                // 领养后放在第一位
                currNode.childs[0] = currNode.previous.childs[currNode.previous.count-1];
                // 当前节点孩子数+1
                currNode.count++;
                // 邻居及节点孩子数-1
                currNode.previous.count--;
                // 返回当前节点
                return currNode;
            }
            return null;
        }else{
            // 合并节点
            int j = 0;
            // 将右边节点的孩子合并到左边节点的孩子列表
            for(int i = currNode.previous.count - 1; i < currNode.count; i--,j++){
                currNode.previous.childs[i] = currNode.childs[j];
            }
            // 合并后节点孩子数 + j
            currNode.previous.count = currNode.previous.count + j;
            // 返回新的节点
            return currNode.previous;
        }
    }


    private boolean less(Key key1,Key key2){
        return key1.compareTo(key2) < 0;
    }

    /**
     * 分裂节点
     * @param currNode 被分裂的当前节点
     * @return 分裂后的右节点
     */
    private Node<Key,Value> split(Node<Key,Value> currNode){

        int grant = DEGREE % 2 == 0 ? DEGREE / 2 : DEGREE / 2 + 1;
        // 必须保证每个节点的孩子数 > DEGREE / 2
        Node<Key,Value> splitNode = new Node(grant);
        // 设置邻居节点
        splitNode.previous = currNode;
        // 修改被分裂节点的孩子数
        currNode.count = DEGREE - grant;
        // 分裂
        for(int start = 0; start < grant ; start++){
            splitNode.childs[start] = currNode.childs[currNode.count + start];
        }
        // 返回分裂的右节点
        return splitNode;
    }

    /**
     * 树节点
     */
    private static class Node<Key,Value>{
        // 孩子数
        int count;
        // 孩子列表
        Entry<Key,Value>[] childs;
        // 邻居节点
        Node<Key,Value> previous;

        public Node(int count){
            this.count = count;
            childs = new Entry[DEGREE];
        }
    }

    /**
     * 节点数据
     * @param <Key> 关键字
     * @param <Value> 值
     */
    private static class Entry<Key,Value>{
        Key key;
        Value val;
        MyBTree.Node next;

        public Entry(Key key,Value val,MyBTree.Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int posistion(Node<Key,Value> node,Key key){
        int pos;
        for(pos = 0; pos < node.count; pos++){
            if(pos + 1 == node.count  || less(key,node.childs[pos + 1].key)){
                return pos;
            }
        }
        return pos;
    }

    private int pos(Node<Key,Value> node,Key key){
        int pos;
        for(pos = 0; pos < node.count; pos++){
            if(less(key,node.childs[pos].key)){
                break;
            }
        }
        return pos;
    }
}
