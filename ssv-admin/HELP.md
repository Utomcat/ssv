# 跨域访问的实现有四种:
[参考链接 详解SpringBoot应用跨域访问解决方案](https://juejin.im/post/6844903991558537223)
[参考链接 Spring Boot2 系列教程(十四)CORS 解决跨域问题](https://blog.csdn.net/u012702547/article/details/102695828)
1. 使用CorsFilter进行全局跨域配置
```java
    @Configuration
    public class GlobalCorsConfig {
        @Bean
        public CorsFilter corsFilter() {
    
            CorsConfiguration config = new CorsConfiguration();
            //开放哪些ip、端口、域名的访问权限，星号表示开放所有域
            config.addAllowedOrigin("*");
            //是否允许发送Cookie信息
            config.setAllowCredentials(true);
            //开放哪些Http方法，允许跨域访问
            config.addAllowedMethod("GET","POST", "PUT", "DELETE");
            //允许HTTP请求中的携带哪些Header信息
            config.addAllowedHeader("*");
            //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            config.addExposedHeader("*");
    
            //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
            UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
            configSource.registerCorsConfiguration("/**", config);
    
            return new CorsFilter(configSource);
        }
    }
```
2. 重写WebMvcConfigurer的addCorsMappings方法（全局跨域配置）(常用的方式)
```java
    @Configuration
    public class GlobalCorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                            .allowedOrigins("*")    //开放哪些ip、端口、域名的访问权限
                            .allowCredentials(true)  //是否允许发送Cookie信息 
                            .allowedMethods("GET","POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
                            .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
                            .exposedHeaders("*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                }
            };
        }
    }
```
3. 使用CrossOrigin注解（局部跨域配置）(此种方式已实验)
```java
    @RestController
    public class HelloController {
    
        @CrossOrigin(value = "http://localhost:8083")
        @GetMapping("/hello")
        public String hello() {
            return "hello";
        }
    
        @CrossOrigin(value = "http://localhost:8083")
        @PostMapping("/hello")
        public String hello2() {
            return "post hello";
        }
        
    }
```
4. 使用HttpServletResponse设置响应头(局部跨域配置)
```java
    @RestController
    public class HelloController {
    
        @RequestMapping("/cors")
        public String cors(HttpServletResponse response){
            //使用HttpServletResponse定义HTTP请求头，最原始的方法也是最通用的方法
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            return "cors";
        }
        
    }
```

```java
public class A {
    public LinkedBlockingDeque(Collection<? extends E> c) {
            this(Integer.MAX_VALUE);
            //从新赋值 防止出现锁竞争的原因
            final ReentrantLock lock = this.lock;
            //reentrantLock 是通过 lock() 来强制刷新内存中的值 从而达到 可见性的目的
            lock.lock(); // Never contended, but necessary for visibility
            try {
                for (E e : c) {
                    if (e == null)
                        throw new NullPointerException();
                    if (!linkLast(new Node<E>(e)))
                        throw new IllegalStateException("Deque full");
                }
            } finally {
                lock.unlock();
            }
    
    }


    public LinkedBlockingDeque(Collection<? extends E> c) {
            this(Integer.MAX_VALUE);
            //reentrantLock 是通过 lock() 来强制刷新内存中的值 从而达到 可见性的目的
                this.lock.lock(); // Never contended, but necessary for visibility
            try {
                for (E e : c) {
                    if (e == null)
                        throw new NullPointerException();
                    if (!linkLast(new Node<E>(e)))
                        throw new IllegalStateException("Deque full");
                }
            } finally {
                lock.unlock();
            }
    }

}
```