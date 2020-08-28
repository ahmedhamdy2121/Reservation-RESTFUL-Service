package cs544.project.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cs544.project.config.domain.Gender;
import cs544.project.config.domain.Location;
import cs544.project.domain.Appointment;
import cs544.project.domain.Reservation;
import cs544.project.domain.User;
import cs544.project.domain.UserRole;
import cs544.project.repository.AppointmentRepository;
import cs544.project.repository.ReservationRepository;
import cs544.project.repository.UserRepository;
import cs544.project.repository.UserRoleRepository;
import cs544.project.service.UserServiceImpl;

@Component
public class DataSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserServiceImpl userService;

    // only enable this for the first time to seed your DB
    // @EventListener
    public void seed(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent");
        addRoles();
        seedUsersTable();
        seedAppointmentsTable();
        seedReservationsTable();
    }

    private UserRole adminRole = new UserRole("ADMIN");
    private UserRole checkerRole = new UserRole("CHECKER");
    private UserRole studentRole = new UserRole("STUDENT");

    @Transactional
    private void addRoles() {

        userRoleRepository.save(adminRole);
        userRoleRepository.save(checkerRole);
        userRoleRepository.save(studentRole);
    }

    @Transactional
    private void seedReservationsTable() {
        User student1 = userRepository.getOne(new Long(3));
        Appointment appointment = appointmentRepository.getOne((long) 1);
        Reservation reservation1 = new Reservation(student1, appointment);
        reservationRepository.save(reservation1);

        User student2 = userRepository.getOne(new Long(5));

        Reservation reservation2 = new Reservation(student2, appointment);
        reservationRepository.save(reservation2);

        User student3 = userRepository.getOne(new Long(6));
        Reservation reservation3 = new Reservation(student3, appointment);
        reservationRepository.save(reservation3);
    }

    private void addUser(User user, UserRole role) {
        // Set<User> userSet = new HashSet();
        Set<UserRole> roleSet = new HashSet<>();

        // userSet.add(user);
        roleSet.add(role);

        user.setRoles(roleSet);
        // role.setUsers(userSet);

        // userRepository.save(user);
        userService.signup(user);
    }

    @Transactional
    private void seedUsersTable() {

        User user1 = new User("Ahmed", "Hamdy", "ahmed.hamdy@miu.edu",
                              Gender.MALE, "ahmed", "ahmed123");
        User user2 = new User("Abdalgalil", "Mustafa", "aaminmustafa@miu.edu",
                              Gender.MALE, "abdo", "abdo123");
        User user3 = new User("Kiros", "Gebregewergs", "KGebregewergs@miu.edu",
                              Gender.MALE, "kiros", "kiros123");
        User user4 = new User("Leilena", "Tekle", "LTekle@miu.edu",
                              Gender.FEMALE, "leilena", "leilena123");
        User user5 = new User("Mohamed", "Sallam", "msallam@miu.edu",
                              Gender.MALE, "mohamed", "mohamed123");
        User user6 = new User("Youssef", "Aziz", "yaziz@miu.edu", Gender.MALE,
                              "youssef", "youssef123");
        User user7 = new User("admin", "admin", "admin@miu.edu", Gender.MALE,
                              "admin", "admin123");

        addUser(user2, adminRole);
        addUser(user1, checkerRole);
        addUser(user4, checkerRole);

        addUser(user3, studentRole);
        addUser(user5, studentRole);
        addUser(user6, studentRole);

        // adding superuser
        Set<UserRole> roleSet = new HashSet<>();
        roleSet.add(adminRole);
        roleSet.add(checkerRole);
        roleSet.add(studentRole);
        user7.setRoles(roleSet);
        userService.signup(user7);
    }

    @Transactional
    private void seedAppointmentsTable() {
        User checker = userRepository.getOne(new Long(2));

        Appointment appointment1 =
                new Appointment(LocalDate.of(2020, Month.JUNE, 8),
                                LocalTime.now(), Location.Argiro, checker);
        Appointment appointment2 =
                new Appointment(LocalDate.of(2020, Month.JUNE, 9),
                                LocalTime.now(), Location.Mclaughin, checker);
        Appointment appointment3 =
                new Appointment(LocalDate.of(2020, Month.JUNE, 10),
                                LocalTime.now(), Location.Argiro, checker);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);

    }

}
