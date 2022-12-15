## Running the application locally

Clone this application in a folder on your local computer, inside the folder
execute the following commands:

```shell
mvn pring-boot:run
```

after this, you can access the link: http://localhost:8080/movies/min-max, you shold see a return like this:

```
{
    "min": [
        {
            "producer": "Joel Silver",
            "interval": 1,
            "previousWin": 1990,
            "followingWin": 1991
        }
    ],
    "max": [
        {
            "producer": "Matthew Vaughn",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ]
}
```

To run the tests, you should type the following command:

```shell
mvn test
```
