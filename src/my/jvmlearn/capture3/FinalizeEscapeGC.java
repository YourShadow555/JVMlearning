package my.jvmlearn.capture3;

/**
 * 此代码演示了两点：
 * 1.对象可以在被GC时自我拯救
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 */
public class FinalizeEscapeGC{
    public static FinalizeEscapeGC SAVE_LOCK = null;

    public void isAlive() {
        System.out.println("yes i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_LOCK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_LOCK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_LOCK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_LOCK != null) {
            SAVE_LOCK.isAlive();
        } else {
            System.out.println("no i am dead::(");
        }
        //下面这段代码一上面的完全相同，但是这次自救却失败了
        SAVE_LOCK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_LOCK != null) {
            SAVE_LOCK.isAlive();
        } else {
            System.out.println("no i am dead :(");
        }
    }
}
