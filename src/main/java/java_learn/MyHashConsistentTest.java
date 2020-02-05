package java_learn;

import java.util.SortedMap;
import java.util.TreeMap;

public class MyHashConsistentTest {
    public static void main(String[] args) {
        ConsistentHashingWithoutVirtualNode chw = new ConsistentHashingWithoutVirtualNode();
        chw.getServer();

        System.out.println();

        ConsistentHashingWithVirtualNode chwvn = new ConsistentHashingWithVirtualNode();
        chwvn.getServer();
    }
}

//不带有虚拟节点的一致性Hash算法
class ConsistentHashingWithoutVirtualNode {

    //一会要添加到hash环里的服务器
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};


    //用TreeMap模拟Hash环
    private static TreeMap<Integer,String> hashCircleMap = new TreeMap<Integer, String>();



    //程序初始化，将所有服务器放入hash环中
    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = HashAlgorithms.getHash(servers[i]);
            hashCircleMap.put(hash,servers[i]);
            System.out.println("[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
        }
        System.out.println();
    }

    public void getServer(){
        String[] objects = {"太阳", "月亮", "星星"};
        for (int i = 0; i < objects.length; i++) {
            int objectHash = HashAlgorithms.getHash(objects[i]);
            SortedMap<Integer,String> subMap = hashCircleMap.tailMap(objectHash);
            if(subMap.isEmpty()){
                int hash = hashCircleMap.firstKey();
                String server = hashCircleMap.get(hash);
                System.out.println("[" + objects[i] + "]的hash值为" + objectHash
                        + ", 被路由到结点[" + server + "]");
            }else {
               int hash = subMap.firstKey();
               String server = subMap.get(hash);
                System.out.println("[" + objects[i] + "]的hash值为" + objectHash
                        + ", 被路由到结点[" + server + "]");
            }
        }
    }
}


//带有虚拟节点的一致性Hash算法
class ConsistentHashingWithVirtualNode {
    //一会要添加到hash环里的服务器
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};




    //用TreeMap模拟Hash环
    private static TreeMap<Integer, String> hashCircleMap = new TreeMap<Integer, String>();




    //程序初始化，将所有服务器放入hash环中
    static {
        for (int i = 0; i < servers.length; i++) {
            for (int j = 0; j < 5; j++) {
                String virtualServer = servers[i] + "&&VS" + j;
                int virtualHash = HashAlgorithms.getHash(virtualServer);
                hashCircleMap.put(virtualHash, virtualServer);
                System.out.println("虚拟服务器[" + virtualServer + "]加入集合中, 其Hash值为" + virtualHash);
            }
        }
        System.out.println();
    }




    public void getServer(){
        String[] objects = {"太阳", "月亮", "星星"};
        for (int i = 0; i < objects.length; i++) {
            String object = objects[i];
            int oHash = HashAlgorithms.getHash(object);
            SortedMap<Integer,String> subMap = hashCircleMap.tailMap(oHash);
            if(subMap.isEmpty()){
                int fHash = hashCircleMap.firstKey();
                String firstVirtualServer = hashCircleMap.get(fHash);
                String realServer = firstVirtualServer.split("&&")[0];
                System.out.println("[" + object + "]的hash值为" + oHash
                        + ", 被路由到结点[" + realServer + "]");
            }else {
                int sHash = subMap.firstKey();
                String virtualServer = subMap.get(sHash);
                String realServer = virtualServer.split("&&")[0];
                System.out.println("[" + object + "]的hash值为" + oHash
                        + ", 被路由到结点[" + realServer + "]");
            }
        }
    }
}




//获得对象的hash值
class HashAlgorithms {
    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    public static int getHash(String str) {
        final int p = 16987653;
        int hash = (int) 2190326631L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 23;
        hash ^= hash >> 8;
        hash += hash << 1;
        hash ^= hash >> 29;
        hash += hash << 10;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }


}
