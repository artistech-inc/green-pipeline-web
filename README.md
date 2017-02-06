# Git Repo for Green Pipeline Web-App

This is a web-app which will utilize various 3rd party apps/scripts for extracting entity information from documents.

## Version 1.3

- Upload file capability
- Joint ERE and ENIE servlets, moderately configured with defaults.
- html visualization (css/js)
- Upload data in a structured manner
- Run Pipeline
- Allow parameters to be set or Pipeline altered
- Output intermediary stages
- Pipeline Run View
- Client side is all jQuery

## Downloading

```sh
git clone https://github.com/artistech-inc/green-pipeline.git
git checkout v1.3
```

## Configuration

Update the [pipeline.yml](https://github.com/artistech-inc/green-pipeline-web/blob/v1.3/src/main/resources/pipeline.yml) file.  Each component must have the proper path value set.  This is the location where the external process will execute from.

- joint_ere
- ENIE
- Merge Tool
- Visualization Generation

The `data-path` value must be somewhere that Tomcat can write to.

## Compilation

This project should be able opened using Netbeans as a Maven Web-App. It will automatically detect the type of project.

The project can also be compiled on the command line directly using maven.

```sh
git clone https://github.com/artistech-inc/pipeline-base.git
cd pipeline-base
git checkout v1.3
mvn clean install
cd ..
git clone https://github.com/artistech-inc/green-pipeline-web.git
cd green-pipeline-web
git checkout v1.3
mvn clean package
```

## Deployment

The output from compilation is in the `target/` directory as `green-pipeline-web-1.3.war`. This war can be deployed to Tomcat's `webapps` directory. Once deployed, it can be accessed via `http://<ip_address:port>/green-pipeline-web-1.3/`.

## Bugs

- Issues and bugs can be e-mailed or registered.

## TODO

- Output console
- Delete data with session timeout

## Future (possibly not in scope)

- Database
- Users
