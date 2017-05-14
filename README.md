# window重复文件查找工具 java版本space sniffer
# a duplication files detection tool of windows
spacesniffer in java

## usage
download the source code, and run "mvn clean package -DskipTests"

then "java -jar FileSniper.jar path size"

path: run program in this path 

size: ignore files smaller than the size(mb)

## changelist
### Version 1.0.0 (2017.5.14)
first official version.

add some user-friendly funtions: you can use fileSniper with few commands.

### Version 0.1.1 (2017.2.28)
add a multithread main MultiThreadFileTraverser。tested well in ssd.

### Version 0.1.0 initial version
basic functions

use md5 as a file's fingerprint, and use a hashmap to store all fingers.

### plans
1. add support for breakpoint resume
2. optimize processing speed

