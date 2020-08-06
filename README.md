# Charts service
Revenue details of Tracks by different Music Artist

###Build
gradle clean build

### Run
./gradlew bootRun

### Endpoint
Get TOP Tracks with highest revenue, number of top rows is contolled by user
/tracks/revenue/max?limit=<>

### Assumptions
1. Total Amount = Units * Amount * currencyToEuroRate
2. CSV files are valid

### Approach
Steps
1. Read the file line by line as stream 
2. Add Track to list to build top grossing tracks of size given by user
2. Sort the list
3. If new track grossing amount is greater than min(grossing list),
   remove min grossing track from list and add new track to list
4 Repeat

### Extension 
1. More test case can be added
2. Design change is applicable

### Pros
It can handle sorting of large file size as complete data 
is not stored in-memory data structure

### Con
File Stream is open until all the processing is finished

# Question
## Outline
Please build a small application with a single endpoint that returns the top 5 tracks that
generated the most revenue based on the two CSV-based streaming reports which
can be found in this GCP bucket.

## Features
1. We expect the endpoint to return a list of tracks with each track containing the
ISRC, track name, artist name and total amount.
2. The total amount should be displayed in EUR, so it will need converting from the
original USD and GBP values in the respective CSV files.

## Additional information
1. We are using Kotlin and Spring Boot, if you feel comfortable working in these
technologies then please do. Otherwise, we would happily accept Java and/or
a different web framework
2. Make sure there's a README with instructions on how to build and run the
application
3. Automated testing is an important part of how we work and we expect you to
include some examples in your solution
4. We are more interested in how you approach the problem than seeing a
complete solution for the entire challenge
5. We expect this task to take 2-4 hours but we purposefully don't specify a hard
time-limit. This is because we don't want this task to take over your weekend, nor
do we want to impose an unnecessarily stressful deadline
6. Please use a private git repository in GitHub, Gitlab or Bitbucket and share the
link with us when you are finished

   
 
