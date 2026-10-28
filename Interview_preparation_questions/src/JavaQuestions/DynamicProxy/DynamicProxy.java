package JavaQuestions.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxy {
//    A dynamic proxy in Java is a mechanism that allows you to create proxy instances at runtime, which can be used to intercept method calls on an interface.
//    This is useful for adding functionality such as logging, transaction management, or access control without modifying the original code.
//    Dynamic proxies are part of the Java Reflection API and are commonly used in frameworks like Spring AOP (Aspect-Oriented Programming).
//    Dynamic proxies in Java are used for a variety of purposes, particularly in scenarios where you want to add, modify,
//    or intercept the behavior of objects at runtime without altering the original code.

//    KEY IDEA (easy memory):
//    Real object  -> does the actual work (HashMap)
//    Proxy object -> fake wrapper that LOOKS like the interface (Map)
//    InvocationHandler -> code that runs BEFORE / INSTEAD OF / AFTER the real method
//
//    Client calls proxy.put(...)
//         -> Proxy forwards to InvocationHandler.invoke(...)
//              -> your before logic
//              -> method.invoke(realObject, args)   // actual HashMap.put
//              -> your after logic
//
//    Why use it?
//    - Logging / metrics
//    - Validation / access control
//    - Transactions (@Transactional in Spring)
//    - Caching
//    Without changing HashMap / your service class code.
//
//    IMPORTANT LIMIT (JDK Dynamic Proxy):
//    Proxy.newProxyInstance works ONLY with INTERFACES (Map, List, your Service interface...)
//    It cannot proxy a concrete class directly.
//    Spring AOP:
//      - Interface present  -> JDK Dynamic Proxy
//      - No interface / class proxy needed -> CGLIB (subclass proxy)
//
//    Proxy.newProxyInstance(classLoader, interfaces[], handler)
//      1) classLoader  -> usually target.getClass().getClassLoader()
//      2) interfaces[] -> which interface(s) the proxy should implement
//      3) handler      -> InvocationHandler that intercepts every method call

    public static void main(String[] args) {

//        Map fees = new HashMap(); // it is simple implementation (NO interception)
//        fees.put("java",2000);
//        System.out.println(fees);


        Map fees = (Map) Proxy.newProxyInstance(  // this code is for dynamic proxy
                HashMap.class.getClassLoader() // class loader of the target type
                , new Class[]{Map.class}       // proxy MUST implement an interface (Map)
                , new ObjectHandler(new HashMap<>()) // real object wrapped by the handler
        );

        System.out.println("1) put java -> allowed (before + real put + after)");
        fees.put("java", 2000); // before/after print; then actual HashMap.put runs

        System.out.println("2) put python -> blocked in handler (real put NOT called)");
        fees.put("python", 1500); // invalid key check; return null without calling HashMap

        System.out.println("3) Final map content (only java should be present):");
        System.out.println(fees);

        // fees is a PROXY, not a HashMap instance
        System.out.println("4) fees is Proxy? " + Proxy.isProxyClass(fees.getClass()));
        System.out.println("   fees class: " + fees.getClass().getName());
    }

}

class ObjectHandler implements InvocationHandler {

    // real / target object whose methods we eventually call
    private final Object object;

    public ObjectHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy  = the proxy instance the client called (fees)
        // method = which method was called (put, get, toString...)
        // args   = method arguments (for put: key, value)

        // logic BEFORE method call
        System.out.println("   before -> " + method.getName());

        // custom rule: block put("python", ...)
        if (method.getName().equals("put") && args != null && args.length > 0) {
            if ("python".equals(args[0])) {
                System.out.println("   invalid key blocked: " + args[0]);
                return null; // skip real method
            }
        }

        // call the REAL method on the real object
        Object result = method.invoke(object, args);

        // logic AFTER method call
        System.out.println("   after  -> " + method.getName());

        return result;
    }
}

// One-Line Summary
// Dynamic Proxy = runtime wrapper around an INTERFACE that intercepts method calls via InvocationHandler
// Used heavily in Spring AOP for @Transactional, logging, security — without changing business code
