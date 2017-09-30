
# Nestor #

Fleetnest is an IoT Fleet Management Cloud platform which collects, analyzes and creates reports for operational cost, performance and safety monitoring of any kind of fleets.

Nestor is part of the FleetNest IoT portal that is a simple guideline to connect the data collector

### What is this 'Nestor' application? ###

* Nestor is small application that helps you to connect to the FleetNest IoT application for data collection.
* Current version is the initial version, **v1.0.0**
* For details, please visit [FleetNest](http://www.fleetnest.com)

### How Nestor is working? ###

* Currently the data is generated through io.generators API. Factories are created for simple data generation.
* Check the _nestor.properties_ under resources folder. _device.data.uniqueId_ must a unique id for your device and it must be configured in the FleetNest application. Default value **34:TR:120** is a sample. The _server.host_ and _server.port_ values are default values.
* All the dependencies are defined in the pom.xml. It uses common libraries as Spring and Apache commons.
* Unit test are implemented both in JUnit4 and JUnit5 syntax. We are trying to use the latest technologies.


### Guidelines ###

* To connect FleetNest, please change the DataService and just call the RestClientService sendData method. This will send the data with a required format.

### Sample JSoN ###

* Sample Json is as follows :

```
{
    "createDate": "2017-09-23 23:12:13",
    "sensors": [
        {
            "coordinate": {
                "latitude": 41.01861,
                "longitude": 29.09645
            },
            "crash": false,
            "createDate": "2017-09-23 23:12:13",
            "distance": 902,
            "emergency": false,
            "engineRunning": true,
            "fuelConsumption": 0.6,
            "humidity": 53,
            "remainingFuel": null,
            "speed": 76,
            "temperature": 19.7,
            "time": 248
        }
    ],
    "uniqueId": "34:TR:120"
}
```
* Multiple sensors can be added. It is a good idea to differentiate multiple sensors with `seconds` level date-time field.

```
{
    "createDate": "2017-09-25 00:04:59",
    "sensors": [
        {
            "coordinate": null,
            "crash": false,
            "createDate": "2017-09-25 00:00:01",
            "distance": null,
            "emergency": false,
            "engineRunning": true,
            "fuelConsumption": null,
            "humidity": null,
            "remainingFuel": null,
            "speed": 39,
            "temperature": null,
            "time": null
        },
        {
            "crash": false,
            "createDate": "2017-09-25 00:00:02",
            "emergency": false,
            "engineRunning": true,
            "speed": 20,
            "temperature": 18.3
        },
        {
            "crash": false,
            "createDate": "2017-09-25 00:00:03",
            "distance": null,
            "emergency": false,
            "engineRunning": false,
            "fuelConsumption": 3.1,
            "speed": 0
        },
        {
            "crash": false,
            "createDate": "2017-09-25 00:00:04",
            "emergency": false,
            "engineRunning": true,
            "speed": 71
        }

    ],
    "uniqueId": "34:TR:120"
}
```

* Fields can be send with null value or just not adding them to the Json.

### Contact Us ###

* Please email to **team@fleetnest.com** for any enquires
