package trying.CollectionTest;

import java.util.LinkedList;

/**
 * @ClassName: LinkedListTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月28日 下午9:51:29
 * 
 *       LinkedList 先进先出
 */
public class LinkedListTest {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("kobe");
		list.add("james");
		list.add("durant");
		System.out.println(list.removeFirst());
		list.addLast("kobe");
		System.out.println(list.removeFirst());
	}
}
