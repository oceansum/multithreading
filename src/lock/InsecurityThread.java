package lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/24 15:52
 */

/**
 * list set 集合线程不安全
 */
public class InsecurityThread {
    public static void main(String[] args) {
        /**
         * ArrayList 线程不安全解决方法
         * 1.Vector
         * 2.Collections
         * 3.CopyOnWriteArrayList
         */
        //List<String > list = new ArrayList<>();
        //List的add方法没有synchronized关键字修饰 是线程不安全的

//        List<String > list = new Vector<>();
        //Vector的add方法有synchronized关键字修饰  是线程安全的  但是Vector比较老 不常使用

        //使用工具类Collections中的synchronizedList()方法 但是比较古老
//        List<String > list = Collections.synchronizedList(new ArrayList<>());

        //CopyOnWriteArrayList方法解决   建议   写时复制技术
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list);
//            }, String.valueOf(i)).start();
//        }


        /**
         * HashSet 线程不安全解决方法
         * CopyOnWriteArraySet
         */
//        Set<String > set = new HashSet<>();
//        Set<String > set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                set.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(set);
//            }, String.valueOf(i)).start();
//        }

        /**
         * HashMap 线程不安全解决方法
         * ConcurrentHashMap
         */
//        Map<String , String > map = new HashMap<>();
        Map<String , String > map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key,UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
