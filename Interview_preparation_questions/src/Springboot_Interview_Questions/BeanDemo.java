package Springboot_Interview_Questions;

//@Component // it is used in Springboot project
public class BeanDemo {

    public BeanDemo(){
        System.out.println("BeanDemo is created");
    }
//    @PostConstruct // we can implement this without Annotation by java with InitializingBean interface it gives afterPropertiesSet method
    public void init() {// here method name whatever you write if it is annotated with @PostConstruct it will run after dependencies injection done
        System.out.println("Bean is being initialized");
    }

//    @PreDestroy // we can implement this without Annotation by java with DisposableBean interface it gives destroy method
    public void destroy() {// here @PreDestroy is used befor the destroy
        System.out.println("Bean is being destroyed");
    }
    //above, two methods are from beans lifecycle
    // now in the main springboot project class
    public static void main(String[] args) {
        //    var container = SpringApplication.run()............ // now we take it as a container
//        BeanDemo beanDemo = container.getBean(BeanDemo.class);
//        System.out.println(beanDemo); // it will firs initialize beanDemo then it prints its sout

    }

}
