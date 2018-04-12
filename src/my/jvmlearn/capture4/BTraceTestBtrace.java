/* BTrace Script Template */
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
	/* put your code here */
    @OnMethod(clazz="my.jvmlearn.capture4.BTraceTest",method="add",location=@Location(Kind.RETURN))
    public static void func(@Self my.jvmlearn.capture4.BTraceTest instance,int a,int b,@Return int result){
        println("���ö�ջ��");
        jstack();
        println(strcat("��������A��",str(a)));
        println(strcat("��������B��",str(b)));
        println(strcat("�������������",str(result)));
    }
}