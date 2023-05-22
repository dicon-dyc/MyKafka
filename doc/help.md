# kafka

## 什么是kafka
kafka是一个分布式的基于发布订阅的消息中间件。主要用于处理流式数据。

## 为什么要使用kafka
1、从个人的角度来讲，我主要工作内容是数仓建设，在离线数仓建设
过程中，其实不太需要引入kafka，除非说是数据量非常的大，在采集的
过程中，一下子全部写入hdfs会造成一定程度的压力。因此可以考虑
用到kafka来进行削峰和缓冲的作用。
2、kafka在数仓中更多的应用场景是在实时数仓建设的过程中，将采集的
数据放入到kafka中通过flink进行实时的计算。不过现在也有了flinkcdc
是否还需要kafka来做缓冲可能需要更多的实践来证明。

## kafka中的ISR、AR又代表什么？ISR的伸缩又指什么。
ISR是kafka中的副本同步队列，主要的作用是备份容灾的作用。
AR是指kafka中的所有的副本。
当ISR中的某个follower落后leader太多就会被踢出到OSR，
新增的副本也会放入到OSR，因此AR=ISR+OSR

## 什么是kafka中的broker
broker是kafka的缓存代理，kafka集群中的一台或多台服务器称为broker，
任何一台broker都可以成为leader在zookeeper的协助下管理和控制整个集群。

## zookeeper在kafka中的作用
zookeeper是一个分布式协调服务组件。早期的kafka版本用zookeeper来存储
一些meta信息，例如consumer的消费状态，group的管理以及offset的值。
新版本中逐渐弱化的了zookeeper的作用，由kafka自己来管理这些信息，但是broker
的管理和协调依然依赖与zookeeper，主要用于选举和检测是否存活。

## kafka顺序写
https://blog.csdn.net/kafka_zsxq/article/details/122297621


## kafkapagecache
https://blog.csdn.net/gx11251143/article/details/107620259
kafka生产者写入数据后首先写入pagecache然后异步写入到磁盘

## kafka零拷贝

## 一旦线上kafka集群有大量消息积压，该如何改进优化

## kafka幂等性
https://www.cnblogs.com/smartloli/p/11922639.html
kafka幂等性主要是保证生产者不会重复发送消息，因为producer在发送消息
时如果没有收到acks那么会进行重试，那么就容易造成重复消息，幂等性的出现
就是为了解决这个问题。幂等性的原理是引入了PID和SequenceNumber，其中PID
是在每个新的producer初始化时，会被分配一个PID。SequenceNumber是指每一个
topic和partiton都对应一个从0开始单调递增的sequencenumber值。当producer重复
发送数据时，而broker通过判断pid和sequencenumber就避免了数据的重复写入。

