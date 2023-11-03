# vim: foldmethod=marker foldmarker={,} foldlevelstart=1 foldlevel=1 ft=dockerfile paste noexpandtab ts=2 sts=2 hls

ARG DOCKER_IMAGE=maven:3.8-jdk-11
FROM ${DOCKER_IMAGE}
ENV MAVEN_CONFIG=/home/maven
ENV JAVA_TOOL_OPTIONS -Duser.home=/home/maven
RUN mkdir -p /home/maven/
WORKDIR /home/maven
COPY pom.xml /home/maven/
COPY src /home/maven/src
CMD mvn -Dmaven.home=/home/maven -e -q -B test -Dmaven.main.skip
RUN mvn -Dmaven.home=/home/maven -e -q -B -U -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true test-compile
RUN chmod -R 777 /home/maven/.m2 /home/maven/target