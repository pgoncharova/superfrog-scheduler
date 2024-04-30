# superfrog-scheduler

## Deployment
1. Install Docker
    Docker is used to containerize the application, making it easy to manage and deploy software.
    Click here (https://docs.docker.com/engine/install/) to be directed to the Docker installation page and follow provided instructions.
2. Clone the Repository
    Clone the Superfrog Scheduler repository (https://github.com/pgoncharova/superfrog-scheduler) from GitHub to a preferred folder using the 'git clone' command in the terminal.
    git clone git@github.com:pgoncharova/superfrog-scheduler.git
    Navigate to the cloned repository folder using the 'cd' command.
    cd superfrog-scheduler
3. Checkout to Preferred Branch
   Use the ‘git checkout’ command to switch to the preferred branch.
   git checkout dev 
4. Build Docker Containers
   Once you are in the repository folder, run the following command to build Docker containers:
   docker-compose up
   This command will create the backend and frontend containers on your localhost.
   Docker containers install all dependencies during the build process, so no additional
   dependencies should be required.

   Once the containers are up and running, Superfrog Scheduler should be accessible and functional. You can
   access the application by navigating to ‘localhost’ or ‘127.0.0.1’ in your web browser.
5. Stop the Application
   To stop the application and the associated Docker containers, use the following command:
   docker-compose down

   This command will stop and remove the containers defined in the ‘docker-compose.yml’ file.


