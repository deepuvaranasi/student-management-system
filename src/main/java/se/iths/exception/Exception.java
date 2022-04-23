package se.iths.exception;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.rest.SubjectRest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Exception {

    public static void studentNotFound(Long id, Student studentFound) {
        if (studentFound == null) {
            throw new WebApplicationException(Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found in database")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }

    public static void sendEmailException() {
        throw new WebApplicationException(Response
                .status(Response.Status.NOT_FOUND)
                .entity("Email already exist!")
                .type(MediaType.TEXT_PLAIN_TYPE).build());
    }

    public static Boolean findStudentByEmail(List<Student> foundStudents, String emailValue) {
        Boolean isPresent = false;
        for (Student student : foundStudents) {
            if (student.getEmail().contains(emailValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }


    public static void sendJsonException(String name) {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity("The name " + name + " exists already!")
                .type(MediaType.APPLICATION_JSON).build());
    }

    public static void sendJsonEMailException(String email) {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity("The email " + email + " already exists!")
                .type(MediaType.APPLICATION_JSON).build());
    }
}
