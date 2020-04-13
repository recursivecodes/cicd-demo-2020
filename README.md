# cicd-demo

## Local DB Setup

Run XE in a Docker image:

### Build

https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance/dockerfiles

### Run

```bash
docker run --name xe -p 1521:1521 -v oracle:/opt/oracle/oradata -e ORACLE_PWD=Str0ngPassw0rd oracle/database:18.4.0-xe
```


