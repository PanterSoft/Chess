# Build Base Image
FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /Chess
ADD . /Chess
CMD sbt run

RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/*\
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_DE.utf8

# Install Project Dependencys
#RUN apt-get install -y sbt libxrender1 libxtst6 libxi6


### How tu Build/Run ###

# Build
#docker build -t chess:v1 .

# Run
#docker run -ti chess:v1