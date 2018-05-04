
Spring-Person example that binds to MongoDB Atlas using a Cloud Foundry user-provided service..

```bash
$ ./mvnw clean package -Dmaven.test.skip=true
$ cf push --no-start
$ cf create-user-provided-service mongodb-atlas -p '{"uri":"mongodb+srv://<username>:<password>@<host>/<dbname>"}'
$ cf bind-service spring-person mongodb-atlas
$ cf start spring-person
```
