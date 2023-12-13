FROM registry.cn-hangzhou.aliyuncs.com/ali-tzn/openjdk17:1

COPY *.jar /data/springboot/app-test.jar

ENTRYPOINT ["java","-jar","/data/springboot/app-test.jar"]

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone


EXPOSE 8080

