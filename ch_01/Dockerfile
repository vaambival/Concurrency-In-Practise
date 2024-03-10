FROM openjdk:21
WORKDIR ./app
RUN mkdir "ch_01"
COPY ThreadSafeCounter.java ch_01/ThreadSafeCounter.java
COPY SeqDemoExample.java ch_01/SeqDemoExample.java
RUN javac ch_01/*.java
ENTRYPOINT ["java", "ch_01/SeqDemoExample"]