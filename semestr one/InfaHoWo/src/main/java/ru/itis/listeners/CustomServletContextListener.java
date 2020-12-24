package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.cars.CarsRepository;
import ru.itis.repositories.cars.CarsRepositoryImpl;
import ru.itis.repositories.files.FilesRepository;
import ru.itis.repositories.files.FilesRepositoryImpl;
import ru.itis.repositories.users.UsersRepository;
import ru.itis.repositories.users.UsersRepositoryImpl;
import ru.itis.services.addCar.AddCarService;
import ru.itis.services.addCar.AddCarServiceImpl;
import ru.itis.services.cars.CarsService;
import ru.itis.services.cars.CarsServiceImpl;
import ru.itis.services.files.FileServiceImpl;
import ru.itis.services.files.FilesService;
import ru.itis.services.signIn.SignInService;
import ru.itis.services.signIn.SignInServiceImpl;
import ru.itis.services.signUp.SignUpService;
import ru.itis.services.signUp.SignUpServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "100878ub$$";
    static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        CarsRepository carsRepository = new CarsRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        FilesService filesService = new FileServiceImpl(filesRepository);
        CarsService carsService = new CarsServiceImpl(carsRepository);
        AddCarService addCarService = new AddCarServiceImpl(carsRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);

        servletContext.setAttribute("addCarService",addCarService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("filesService", filesService);
        servletContext.setAttribute("carsService", carsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

