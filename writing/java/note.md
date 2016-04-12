
##IDEA maven项目设置LanguageLevel和JavaCompiler

idea 中新建的pom项目,就算使用的JDK是1.8,可是 LanguageLevel JavaCompiler总是默认1.5。查找了一下[解决IDEA自动重置LanguageLevel和JavaCompiler版本的问题][] 这个应该是说明的比较清楚了
[解决IDEA自动重置LanguageLevel和JavaCompiler版本的问题]:http://blog.csdn.net/isea533/article/details/48575983

即使在IDEA中设置了LanguageLevel和JavaCompiler ,POM.xml一更新,还是会被重置掉
还是添加pom的设置 如下设置

    <build>
      <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3.2</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
      </plugins>
    </build>

<version>2.3.2</version> 可以不设置。大概会用最新稳定版
<groupId>org.apache.maven.plugins</groupId> 也可以不设置，大概会使用默认插件查询?

默认的1.5哪里来的呢?查找了一下,maven-compiler-plugin-3.1.jar/META-INF/maven/plugin.xml    

        <source implementation="java.lang.String" default-value="1.5">${maven.compiler.source}</source>
        <staleMillis implementation="int" default-value="0">${lastModGranularityMs}</staleMillis>
        <target implementation="java.lang.String" default-value="1.5">${maven.compiler.target}</target>
大概就是这里是默认选项