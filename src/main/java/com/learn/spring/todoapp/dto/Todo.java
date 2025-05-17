// package com.learn.spring.todoapp.dto;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.validation.constraints.Size;
// import lombok.Getter;
// import lombok.Setter;

// import java.time.LocalDate;

// @Setter
// @Getter
// @Entity
// public class Todo {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int id;
//     private String username;
//     @Size(min = 10, message = "Please enter min 10 characters!")
//     private String description;
//     private LocalDate targetDate;
//     private boolean done;

//     public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done) {
//         super();
//         this.id = id;
//         this.username = username;
//         this.description = description;
//         this.targetDate = targetDate;
//         this.done = done;
//     }

//     public Todo() {

//     }

//     @Override
//     public String toString() {
//         return "Todo{" +
//                 "id=" + id +
//                 ", username='" + username + '\'' +
//                 ", description='" + description + '\'' +
//                 ", targetDate=" + targetDate +
//                 ", done=" + done +
//                 '}';
//     }
// }
