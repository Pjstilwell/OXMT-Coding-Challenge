OxmtCodingChallenge - Machine Age Validator

This application takes in a list of machines in the form { machineID: number, age: String }, where age
is of the form "<number> <time unit>" and <time unit> is in [day/s, week/s, month/s, year/s]. The app
will return the list of machines that have ages found to be outliers in the data, likely by errornous user
input. For example, "90 years" inputted instead of "90 days".

After converting the ages of all machines into days, the app uses a machine's age's z-score against all
other inputted machines to determine if a machine is an outlier or not.

To process the data please send the list of machines in the request body to:
@POST 'http://localhost:8080/api/v1/findOutliers'

Maven v4.0.0, Spring Boot v3.3.3, Java 22