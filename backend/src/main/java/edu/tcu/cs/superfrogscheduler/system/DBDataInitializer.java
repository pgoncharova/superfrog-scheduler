package edu.tcu.cs.superfrogscheduler.system;

import edu.tcu.cs.superfrogscheduler.customer.CustomerRepository;
import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestRepository;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogRepository;
import edu.tcu.cs.superfrogscheduler.user.SuperfrogUser;
import edu.tcu.cs.superfrogscheduler.user.SuperfrogUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final RequestRepository requestRepository;

    private final SuperfrogRepository superfrogRepository;

    private final CustomerRepository customerRepository;

    private final SuperfrogUserService superfrogUserService;

    public DBDataInitializer(RequestRepository requestRepository,
                             SuperfrogRepository superfrogRepository, CustomerRepository customerRepository, SuperfrogUserService superfrogUserService) {
        this.requestRepository = requestRepository;
        this.superfrogRepository = superfrogRepository;
        this.customerRepository = customerRepository;
        this.superfrogUserService = superfrogUserService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create some requests
        Request r1 = new Request();
        r1.setId("123456789");
        r1.setFirstName("John");
        r1.setLastName("Doe");
        r1.setPhoneNumber("(123) 456-7890");
        r1.setEmail("j.doe@tcu.edu");
        r1.setEventType("Private/Residential Event");
        r1.setEventTitle("Birthday Party!");
        r1.setOrganizationName("N/A");
        r1.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        r1.setOnCampus(false);
        r1.setSpecialInstructions("Please park in the parking lot!");
        r1.setBenefitsDescription("Water and pizza will be provided.");
        r1.setSponsorDescription("N/A");
        r1.setDetailedDescription("This event is my son's birthday party.");

        Request r2 = new Request();
        r2.setId("1231231231");
        r2.setFirstName("Elizabeth");
        r2.setLastName("Smith");
        r2.setPhoneNumber("(333) 444-5555");
        r2.setEmail("e.smith@tcu.edu");
        r2.setEventType("TCU Event");
        r2.setEventTitle("TCU E-sports Iron Skillet");
        r2.setOrganizationName("TCU E-sports CLub");
        r2.setEventAddress("3100 McFarlin Blvd, Dallas, TX 75205");
        r2.setOnCampus(false);
        r2.setSpecialInstructions("Free parking for TCU attendees opposite of provided address.");
        r2.setBenefitsDescription("Each attendee will receive a ticket for food - redeemable between 10am and 3pm - at the cafeteria located in the basement of Hughes-Trigg Student Center.");
        r2.setSponsorDescription("SMU E-sports Club will be co-sponsoring this event alongside TCU E-sports Club.");
        r2.setDetailedDescription("The event will be streamed live on https://www.twitch.tv/smu_esports");

        this.requestRepository.save(r1);
        this.requestRepository.save(r2);

        // Create some superfrog students
        Superfrog s1 = new Superfrog();
        s1.setEmail("thebestfrog@tcu.edu");
        s1.setFirstName("Super");
        s1.setLastName("Frog");
        s1.setPhoneNumber("(888) 888-8888");
        s1.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");

        Superfrog s2 = new Superfrog();
        s2.setEmail("lizard@tcu.edu");
        s2.setFirstName("Horned");
        s2.setLastName("Lizard");
        s2.setPhoneNumber("(222) 222-2222");
        s2.setPhysicalAddress("3100 Main Drive, Fort Worth, TX 76129");

        Superfrog s3 = new Superfrog();
        s3.setEmail("spirit@tcu.edu");
        s3.setFirstName("Spirit");
        s3.setLastName("Director");
        s3.setPhoneNumber("(123) 222-2222");
        s3.setPhysicalAddress("Sadler Hall, Fort Worth, TX 76129");

        this.superfrogRepository.save(s1);
        this.superfrogRepository.save(s2);
        this.superfrogRepository.save(s3);

        // Create users
        SuperfrogUser u1 = new SuperfrogUser();
        u1.setEnabled(true);
        u1.setUsername(s1.getEmail());
        u1.setId(1);
        u1.setPassword("hello");
        u1.setRoles("superfrog");

        SuperfrogUser u2 = new SuperfrogUser();
        u2.setEnabled(true);
        u2.setUsername(s2.getEmail());
        u2.setId(2);
        u2.setPassword("hello");
        u2.setRoles("superfrog");

        SuperfrogUser u3 = new SuperfrogUser();
        u3.setEnabled(true);
        u3.setUsername(s3.getEmail());
        u3.setId(3);
        u3.setPassword("hello");
        u3.setRoles("superfrog spiritdirector");

        this.superfrogUserService.save(u1);
        this.superfrogUserService.save(u2);
        this.superfrogUserService.save(u3);

        // Create some customers
        /**
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPhoneNumber("123-456-7890");

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Jane");
        c2.setLastName("Smith");
        c2.setEmail("jane.smith@example.com");
        c2.setPhoneNumber("987-654-3210");

         * Customer c3 = new Customer();
         * c3.setId(3L);
         * c3.setFirstName("Emma");
         * c3.setLastName("Johnson");
         * c3.setEmail("emma.johnson@example.com");
         * c3.setPhoneNumber("(555) 123-4567");
         *
         * customerRepository.save(c1);
         * customerRepository.save(c2);
         */



    }
}
