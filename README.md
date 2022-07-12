# Makers Aceplay Cloudless Spring Boot

This is a seed project for the Aceplay Cloudless Engineering Project.

It comes set up with:

* The JUnit5 test framework.
* The Spring Boot web framework.
* The Spring Security framework & JWT for auth.
* The PostgreSQL database.
* The JPA ORM

Several features are implemented. Your task is to work through the tickets in
the `docs/` directory.

## Getting the project set up

### Stage One: Project & Tests

Open this directory using [IntelliJ](https://www.jetbrains.com/idea/). You may
need to wait for the dependencies to install.

Then, in the Gradle sidebar:

* To run the tests: `Aceplay -> Tasks -> Verification -> Test`

If you encounter any problems, they are not intended. Contact your coach for
advice.

### Stage Two: Development Server

Then create the development database:

```shell
$ createdb makers-aceplay-dev
$ # You'll see nothing if it worked
```

Finally, to run the server, open the Gradle sidebar and run `Aceplay -> Tasks ->
Application -> bootRun`.

When the server has started successfully, you'll be able to visit 
`http://localhost:8080/` and see the application running.

## Documentation

This application is partially documented. Some documentation is more extensive
than you would find in the real world, and some areas leave room for you to
discover.

:exclamation: Important :exclamation:: some of the videos include references to
the Cloud (Google Cloud Platform and its services). To make it clear, as part of
this project you WILL NOT deploy your work to the Cloud. However, if you are
curious and have enough time, we recommend you pay attention to these bits too
as they will increase your overall knowledge and will allow you to reflect on
different approaches and consider trade-offs when implementing systems.

* **[A video demo of the application](https://www.youtube.com/watch?v=oZ1avb8s61c&t=0s)**

* **[A video demo of the JSON API](https://www.youtube.com/watch?v=oZ1avb8s61c&t=805s)**

* **[A video demo of how Spring handles
  requests](https://www.youtube.com/watch?v=oZ1avb8s61c&t=1138s)**

* **[An explanation of Spring annotations like
  `@Autowired`](https://www.youtube.com/watch?v=oZ1avb8s61c&t=1614s)**  
  And how it relates to inversion of control.

* **[Create User Request Flow](docs/Create%20User%20Request%20Flow.pdf)**  
  A PDF of the flow of events when creating a new user and session. [You can
  view a video walkthrough
  here.](https://www.youtube.com/watch?v=oZ1avb8s61c&t=2408s)

There are links most files of the code to video walkthroughs of that particular
file.

There is a full playlist of all of the video resources
[here](https://www.youtube.com/playlist?list=PLEpm7HyVZ61CHFjkDWHLDJgzZCBTpXNIO),
though we would recommend using them only they become useful in the project.
