//package bank_management;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    private int id;
//    private String name;
//    private String fatherName;
//    private String email;
//    private LocalDate dateOfBirth;
//
//    @Embedded
//    @Column(name = "Address")
//    private Address address;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "gender")
//    private Gender gender;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "Martial_status")
//    private MartialStatus martialStatus;
//
//
//}
