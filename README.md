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

## Requirement
Please build a small application with a single endpoint that returns the top 5 tracks that
generated the most revenue based on the two CSV-based streaming reports.

   
 
