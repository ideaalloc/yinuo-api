# 宜诺API服务

本项目是宜诺API服务的开源版本

### Deploy it

1. Execute the SQL script in MySQL `src/main/db/mysql/schema.sql`
2. Modify `application.properties`, for DB urls and logs location; `application-prod.properties` is for the production env
3. Package it, `mvn clean package -DskipTests`

### Run it
```
$ java -Xms128m -Xmx128m -jar target/api-0.0.1-SNAPSHOT.jar
```

Open http://localhost:9080/index.html

### Update

Use basic authentication to authenticate app client:

Authenticate to get a token:

```
$ curl -H "Content-Type: application/json" -X POST -u clientId:clientSecret http://api.yinuo-tech.com/v1/connect
```

Then use the token to access strict resources:

```
$ curl -H "Authorization: Bearer 13ce760a-92de-44f1-sgh3-62764ed65ef1" -H "Content-Type: application/json" -X POST -d '{"password": "888888","role": "ROLE_PATIENT","username": "test1"}' http://api.yinuo-tech.com/v1/users/register
```

Use basic authentication to authenticate user:

Authenticate to get a token:

```
$ curl -H "X-Auth-Username: patient1" -H "X-Auth-Password: 888888" -H "Content-Type: application/json" -X POST http://api.yinuo-tech.com/v1/authenticate
```
Then use the token to access strict resources:

```
$ curl -H "X-Auth-Token: 295056ee-a373-48e4-aad3-7669ebac216e" -H "Content-Type: application/json" -d '{"age": "string", "comments": "string", "name": "string", "number": "string", "phone": "string", "phonedoc": "string", "sex": "string"}' http://api.yinuo-tech.com/v1/patients/query
```

### Metrics

```
$ curl -H "X-Auth-Username: admin" -H "X-Auth-Password: servicerequest" -H "Content-Type: application/json" -X GET http://api.yinuo-tech.com/metrics
```

### API Doc

http://api.yinuo-tech.com/raml/index.html

http://api.yinuo-tech.com/swagger-ui.html

The OAuth2 module as shown below has been removed temporarily

```
$ curl localhost:9080/oauth/token -d "grant_type=password&scope=read&username=greg&password=turnquist" -u foo:bar
```
Return

```
{"access_token":"28d87bab-9048-4fa3-b0df-286a913fef5b","token_type":"bearer","refresh_token":"1979329a-dd48-45d2-b1bc-bc6ed53627fc","expires_in":43198,"scope":"read"}
```

```
$ curl -H "Authorization: bearer 28d87bab-9048-4fa3-b0df-286a913fef5b" localhost:9080/v1/flights/1
```

Return

```
{"id":1,"origin":"Nashville","destination":"Dallas","airline":"Spring Ways","flightNumber":"OAUTH2","traveler":"Greg Turnquist"}
```

### For Client Developer

```
$ curl http://api.yinuo-tech.com/oauth/token -d "grant_type=password&scope=read&username=greg&password=turnquist" -u foo:bar
```

Return

```
{"access_token":"5f65427c-44ca-469e-adf8-978072e600b8","token_type":"bearer","refresh_token":"11ddc069-5a2b-406b-a13b-c10f93e0a83c","expires_in":43199,"scope":"read"}
```

```
$ curl -H "Authorization: bearer 5f65427c-44ca-469e-adf8-978072e600b8" http://api.yinuo-tech.com/v1/flights/1
```

Return

```
{"id":1,"origin":"Nashville","destination":"Dallas","airline":"Spring Ways","flightNumber":"OAUTH2","traveler":"Greg Turnquist"}
```

### Release it

访问SWAGGER:
http://api.yinuo-tech.com/swagger-ui.html

访问RAML:
http://api.yinuo-tech.com/raml/index.html?raml=/raml/api/flight.raml
