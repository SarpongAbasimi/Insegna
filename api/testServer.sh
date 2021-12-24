#!/bin/bash
echo "Rule No1 - Ensure that the server is running 🎲"
echo "Sending request to localhost:event/api/v1/event"

for i in {1..1}
do
  curl --request POST -sL -i -v \
       --header "Content-Type: application/json" \
       --url 'http://localhost:8080/api/v1/event'\
       --data '{
   		  "userName": "Beatice",
    		"message" : "I have completed the server part of the app",
    		"mood": "I feel great today 🙂",
    		"whatIPlanToAchieve": "Tomorrow I want to just sleep"
    		}'
  echo "Request number $i"
done
