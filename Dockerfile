# Build Base Image
FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /Chess
ADD . /Chess

RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/*\
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_DE.utf8

RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6
RUN apt-get install apt-transport-https curl gnupg -yqq

RUN apt install -y xauth
#RUN xauth add MacBook-Pro-von-Nico-Mattes.fritz.box/unix:0  MIT-MAGIC-COOKIE-1  54659796d3cf6ac68b016d4d98ef2c9d
RUN xauth list

RUN sbt update
CMD sbt run

### How tu Build/Run ###

# Build
#docker build -t chess:v1 .

# Run with x11 on macos
# xhost +
# ip=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}')
# docker run -e DISPLAY=$ip:0 -v /tmp/.X11-unix:/tmp/.X11-unix -ti chess:v1
# xhost -