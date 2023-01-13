
FROM hseeberger/scala-sbt:8u141-jdk_2.12.3_1.0.2
#8u222_1.3.5_2.13.1
WORKDIR /Chess
ADD . /Chess
CMD sbt run
#Then build the image with a tag
#docker build -t chess:v1 .
#And run your app
#docker run -ti chess:v1