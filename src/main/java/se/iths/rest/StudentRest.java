package se.iths.rest;


import se.iths.entity.Student;
import se.iths.service.StudentService;
import se.iths.exception.Exception;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  StudentRest {

    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("new")
    @POST
    public Response createStudent(Student student) {

        List<Student> studentsFound = studentService.getAllStudents();
        studentService.createStudent(student);
        String emailValue = student.getEmail();

        if (Exception.findStudentByEmail(studentsFound, emailValue)) {
            Exception.sendEmailException();
        }
        return Response.ok(student).build();
    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student studentFound = studentService.findStudentById(id);
        Exception.studentNotFound(id, studentFound);
        return Response.ok(studentFound).build();

    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        List<Student> studentsFound = studentService.getAllStudents();
        return Response.ok(studentsFound).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.noContent().build();

    }


    @Path("findbyLastname")
    @GET
    public Response findByLastname(@QueryParam("findbyLastname") String lastName) {
        List<Student> studentFound = studentService.findByLastname(lastName);
        return Response.ok(studentFound).build();

    }


    }
