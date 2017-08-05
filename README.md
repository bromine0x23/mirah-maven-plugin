# Mirah Maven Plugin

Plugin to allow to compile Mirah code with Maven.

## Usage

1. Add the plugin to your pom.xml
```xml
<build>
...
<plugin>
    <groupId>cn.bromine0x23.mirah</groupId>
    <artifactId>maven-mirah-plugin</artifactId>
    <executions>
        <execution>
            <phase>compile</phase>
            <goals>
                <goal>compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
...
</build>
```

2. Execute `mvn compile`

## Configuration options

Those are the options that can be modified for this plugin, this example shows the
default ones so we just need to modify them into the plugin declaration:
```xml
<plugin>
...
<configuration>
    <sourceDirectory>src/main/mirah</sourceDirectory>
    <outputDirectory>target/classes</outputDirectory>
    <target>1.8</verbose>
    <verbose>false</verbose>
</configuration>
...
</plugin>
```
