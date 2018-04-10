package my.jvmlearn.capture2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {
    /*public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        //10M的PermSize在integer范围内足够产生OOM了
        int i=0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }*/

    public static void main(String[] args) {
        /*String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("jav").append("a").toString();
        System.out.println(str2.intern() == str2);*/


        String str3 = new String("学不会");
        String str4 = new String("学") + new String("不会");
        String str5 = "学" + "不会";
        String str6 = "学不会";
        System.out.println(str5==str6);
        System.out.println(str3==str5);
        System.out.println(str3 == str4);
        System.out.println(str3.intern()==str5);
    }
}
/**
 * 自jdk1.7以来 把原本存放在方法区的字符串常量池移出，所以限制方法区的大小，无法限制字符串常量池内存
 */
