
                                                    数据结构笔记
  一、抽象的数据类型（ADT）

        1. ADT 是一组带有操作的对象集合，如表、树等。他们是一个数据集合的抽象。

  二、 表

        1.普通表

            1.1 add()、remove() 在前端操作是花费高昂的(O(n)),所有的数据都要向后（前）移动一位。
            1.2 findKth(i) 该操作花费为 O(1)

        2.链表

            2.1 add()、remove() 在前端操作是花费高昂的(O(1)),所有的数据只是交换一下前后的引用。（还有分配空间）
            2.2 findKth(i) 该操作花费wei O(n),该操作没有普通表的高效( 因为普通表的存储是连续的,地址及计算是O(1) )。

        3.Collcetion 接口

            3.1 是 java 对数据结构的实现的定义。表（ArrayList、linkedList）就是其中的一种定义。

            3.2 其中 Collection 接口扩展了 Iterator 接口 ,
                每一个Collection 都要提供Iterator的实现（提供该接口的迭代方式）。

            3.3 Collection的实现能否使用ForEach,需要实现Iterator。ForEach依赖Iterator.

       4.Iterator remove()

            4.1