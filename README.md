# Weather Application using spring boot

- This application provide user the weather information given the city
- Currently using the OpenWeather API for fetching the weather information


## OpenWeather API

- generate the API key by logging into the account. it gives 1000 free api calls per day

### Sample URI
```shell
https://api.openweathermap.org/data/2.5/weather?q={city_name}&appid=72713e42648bffbf57f785add8d2eb53
```

### Sample Response
```json
{
	"coord":{
		"lon":77.2167,
		"lat":28.6667
	},
	"weather":[
		{
			"id":701,
			"main":"Mist",
			"description":"mist",
			"icon":"50n"
		}
	],
	"base":"stations",
	"main":{
		"temp":284.2,
		"feels_like":283.79,
		"temp_min":284.2,
		"temp_max":284.2,
		"pressure":1018,
		"humidity":93,
		"sea_level":1018,
		"grnd_level":992
	},
	"visibility":1500,
	"wind":{
		"speed":1.54,
		"deg":250
	},
	"clouds":{
		"all":20
	},
	"dt":1735568271,
	"sys":{
		"type":1,
		"id":9165,
		"country":"IN",
		"sunrise":1735523005,
		"sunset":1735560221
	},
	"timezone":19800,
	"id":1273294,
	"name":"Delhi",
	"cod":200
}
```

