# API Gateway

API Gateway is an implementation of the Gateway + Backend for Front-End pattern. This service is going to be the central point of entry into APIs i.e, only the APIs exposed by this gateway will be usable any of the clients, which for now includes the UI only.

In a border perspective this service is going to provide the following:

- Facade APIs which will interact with individual micro-services and provide an output suitable for the respective Front-End as already described above.

# Building from Source
Clone the git repository using the URL on the Gitlab home page:

    $ git clone https://github.com/muhammadaliqasmi/api-gatway.git
    $ cd api-gateway

## Command Line
Use Maven 2.2 or 3.0, then on the command line:

   $ mvn clean install

## SpringSource Tool Suite (STS)
In STS (or any Eclipse distro or other IDE with Maven support), import the module directories as existing projects.  They should compile and the tests should run with no additional steps.
