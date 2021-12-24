package aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;


@Interceptor
@ExecutionTimeLogger
public class ExecutionTimeLoggerInterceptor implements Serializable {

    @AroundInvoke
    public Object computeTime(final InvocationContext invocationContext) throws Exception {
        String methodName = invocationContext.getMethod().getName();
        String className = invocationContext.getClass().getName();

        long startTime = System.nanoTime();
        Object result = invocationContext.proceed();
        long stopTime = System.nanoTime();
        String resultTime = String.valueOf(stopTime - startTime);
        try {
            System.out.println("--------------------------------------");
            System.out.println("Interceptor invoked");
            System.out.println("Time of execution the method '" +methodName+ "'" + ": " + resultTime + " nano seconds");
            System.out.println("--------------------------------------");
        } catch (Exception e) {
            System.out.println("Inteceptor Error: " + e.getMessage());
        }


        return (Object)result;
    }
}
