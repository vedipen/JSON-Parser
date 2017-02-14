JSON-Parser
-----------
This is a simple JSON Parser which displays the keys whenever curl POST command is executed, while the server is running.

Local Server - 4444

To run the *server* -

    mvn compile 

    mvn exec:java -Dexec.mainClass="com.vedipen.App"

*API* -

    curl localhost:4444/get_keys -X POST -d '{"adf":"Qwer","dfg":"qwer"}'

Use this curl command with any JSON Objects/strings and it will display the keys which can be processed later on.

Testnggggg.............
test
Please ignore
