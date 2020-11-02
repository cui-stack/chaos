mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
spring:
  datasource:
    url: jdbc:mysql://firepongo.tech:3306/hey?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
  redis:
    database: 0
    host: firepongo.tech
    password: null
  dubbo:
    registry:
      address: zookeeper://firepongo.tech:2181
    consumer:
      check: false
  rabbitmq:
    addresses: firepongo.tech
    username: guest
    password: guest
