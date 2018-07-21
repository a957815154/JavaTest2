mvn package
cd Exam1/target
echo "this is answer 1"
java -jar Exam1.jar
cd ../..
echo "this is answer 2"
#指定开启服务器指令
java -cp Exam2/target/Exam2.jar com.hand.Server
#指定开启客户端指令
#java -cp Exam2.jar com.hand.Client
echo "this is answer 3"
java -jar Exam3/target/Exam3.jar